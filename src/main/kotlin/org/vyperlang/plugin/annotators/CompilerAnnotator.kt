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

data class FileInfo(val file: PsiFile, val indicator: ProgressIndicator? = null)

private val errorRegexes = listOf(
    "(\\w+): ([^\\n]+)\\n+( +\\(hint: [^)]+\\)\\n+)? +(?:contract \"[^\"]+\", function \"[^\"]+\", )?line (\\d+):(\\d+)"
).map { it.toRegex(RegexOption.MULTILINE) }

val LOG: Logger = Logger.getInstance(CompilerAnnotator::class.java)

/**
 * Annotator that listens to the compiler output and annotates the file accordingly
 */
class CompilerAnnotator : ExternalAnnotator<FileInfo, List<CompilerError>>(), DumbAware {

    override fun collectInformation(file: PsiFile, editor: Editor, hasErrors: Boolean): FileInfo {
        return FileInfo(file)
    }

    override fun doAnnotate(info: FileInfo?): List<CompilerError> {
        val file = info!!.file
        val result = VyperCompilerDocker(file.project, file.virtualFile, info.indicator).run()
        return when (result.statusDocker) {
            StatusDocker.SUCCESS -> listOf()
            StatusDocker.FAILED -> parseErrors(result.stderr)
            else -> emptyList()
        }
    }

    private fun parseErrors(stderr: String): List<CompilerError> {
        val messages = errorRegexes.flatMap { it.findAll(stderr) }.map {
            val (errorType, message, hint, line, column) = it.destructured
            CompilerError(errorType, message, hint, line.toInt(), column.toInt())
        }
        if (messages.isEmpty()) {
            LOG.warn("No error messages found in compiler output: $stderr")
        }
        return messages
    }

    override fun apply(file: PsiFile, annotationResult: List<CompilerError>, holder: AnnotationHolder) {
        annotationResult.forEach {
            val element = file.findElementAt(
                file.textOffset + file.text.lines().take(it.line - 1).sumOf { it.length + 1 } + it.column)
            if (element != null) {
                holder.newAnnotation(
                    HighlightSeverity.ERROR,
                     it.message
                ).range(element.textRange)
                    .tooltip(if (it.hint.isNullOrBlank()) it.errorType else it.hint)
                    .create()
            }
        }
    }
}

data class CompilerError(val errorType: String, val message: String, val hint: String?, val line: Int, val column: Int)
