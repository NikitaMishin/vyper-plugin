package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.ExternalAnnotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiFile
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.VyperCompilerDocker
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.vyperlang.plugin.docker.CompilerMissingError

data class FileInfo(val project: Project, val file: VirtualFile, val indicator: ProgressIndicator? = null)

private val errorRegexes = listOf(
    // ErrorType: error message\n
    "(\\w+): ([^\\n]+)\\n+" +
            // (hint: optional)\n
            "( +\\(hint: [^)]+\\)\\n+)?" +
            // contract "x", function "y", line 1:1
            " +(?:contract \"[^\"]+\", )?(?:function \"[^\"]+\", )?line (\\d+):(\\d+)"
).map { it.toRegex(RegexOption.MULTILINE) }

val LOG: Logger = Logger.getInstance(CompilerAnnotator::class.java)

/**
 * Annotator that listens to the compiler output and annotates the file accordingly
 */
class CompilerAnnotator : ExternalAnnotator<FileInfo, List<CompilerError>>(), DumbAware {

    override fun collectInformation(file: PsiFile) = FileInfo(file.project, file.virtualFile)

    override fun collectInformation(file: PsiFile, editor: Editor, hasErrors: Boolean) = collectInformation(file)

    override fun doAnnotate(info: FileInfo?): List<CompilerError> {
        val result = try {
            VyperCompilerDocker(info!!.project, info.file, info.indicator).run()
        } catch (e: CompilerMissingError) {
            LOG.error("Error while running compiler annotator", e)
            null
        }
        if (result?.statusDocker == StatusDocker.FAILED) {
            return parseErrors(result.stderr);
        }
        return emptyList()
    }

    private fun parseErrors(stderr: String): List<CompilerError> {
        val messages = errorRegexes.flatMap { it.findAll(stderr) }.map {
            val (errorType, message, hint, line, column) = it.destructured
            CompilerError(errorType, message.trim(), hint, line.toInt(), column.toInt())
        }
        if (messages.isEmpty()) {
            LOG.warn("No error messages found in compiler output: $stderr")
        }
        return messages
    }

    override fun apply(file: PsiFile, annotationResult: List<CompilerError>, holder: AnnotationHolder) {
        annotationResult.forEach {
            val offset = file.text.lines().take(it.line - 1).sumOf { it.length + 1 } + it.column
            val element = file.findReferenceAt(file.textOffset + offset)?.element
                ?: file.findElementAt(offset)
                ?: file.findElementAt(offset - 1)
                ?: file.findElementAt(offset - 1)
                ?: file
            holder.newAnnotation(
                HighlightSeverity.ERROR,
                it.message
            ).range(element.textRange)
                .tooltip(if (it.hint.isNullOrBlank()) it.errorType else it.hint)
                .create()
        }
    }
}

data class CompilerError(val errorType: String, val message: String, val hint: String?, val line: Int, val column: Int)
