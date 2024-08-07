package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.ExternalAnnotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import com.intellij.util.text.SemVer
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.docker.CompilerMissingError
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.ToolResult
import org.vyperlang.plugin.docker.VyperCompilerDocker
import org.vyperlang.plugin.psi.file

data class FileInfo(val project: Project, val file: VirtualFile, val version: SemVer?, val indicator: ProgressIndicator? = null)

val VYPER_ERROR_REGEX = listOf(
    // ErrorType: error message\n
    "(\\w+): ([^\\n]+)\\n+",
    // (hint: optional)\n
    "( +\\(hint: [^)]+\\)\\n+)?",
    // contract "x", function "y", line 1:1
    " +(?:contract \"[^\"]+\", )?(?:function \"[^\"]+\", )?line (\\d+):(\\d+)"
).joinToString("").toRegex(RegexOption.MULTILINE)

val LOG: Logger = Logger.getInstance(CompilerAnnotator::class.java)

/**
 * Annotator that calls the compiler and annotates the file accordingly.
 * By using the ExternalAnnotator, the compiler is only called when the file is saved and all background processes are finished.
 */
class CompilerAnnotator : ExternalAnnotator<FileInfo, List<CompilerError>>(), DumbAware {

    /** 1st step of the external annotator: Collect information needed to run the compiler. */
    override fun collectInformation(psiFile: PsiFile) = FileInfo(psiFile.project, psiFile.virtualFile, psiFile.file.vyperVersion)

    /** 1st step of the external annotator: Collect information needed to run the compiler. */
    override fun collectInformation(psiFile: PsiFile, editor: Editor, hasErrors: Boolean) = collectInformation(psiFile)

    /** 2nd step of the external annotator: Run the compiler and return the result. */
    override fun doAnnotate(info: FileInfo?): List<CompilerError> {
        if (info?.file?.exists() != true || info.file.fileType !== VyperFileType.INSTANCE) {
            return emptyList() // file may not be written to disk yet, also occurs in unit tests with `configureByText`
        }
        val result = try {
            VyperCompilerDocker(info.project, info.file, info.version, info.indicator).run()
        } catch (e: CompilerMissingError) {
            LOG.error("Error while running compiler annotator", e)
            e.notify(info.project)
            null // todo: return warning to the editor
        }
        if (result?.statusDocker == StatusDocker.FAILED) {
            return parseErrors(info, result);
        }
        return emptyList()
    }

    /** 3rd step of the external annotator: Apply the annotations to the file. */
    override fun apply(file: PsiFile, annotationResult: List<CompilerError>, holder: AnnotationHolder) {
        annotationResult.forEach {
            val offset = file.text.lines().take(it.line - 1).sumOf { it.length + 1 } + it.column
            val element = file.findReferenceAt(file.textOffset + offset)?.element
                ?: file.findElementAt(offset)
                ?: file.findElementAt(offset - 1)
                ?: file.findElementAt(offset - 2)
                ?: file
            holder.newAnnotation(HighlightSeverity.ERROR, it.message)
                .range(element.textRange)
                .tooltip("${it.message} ${if (it.hint.isNullOrBlank()) "(${it.errorType})" else it.hint.trim()}")
                .create()
        }
    }

    /** Parse the compiler stderr, return list of errors. */
    private fun parseErrors(info: FileInfo, result: ToolResult): List<CompilerError> {
        val messages = VYPER_ERROR_REGEX.findAll(result.stderr).map {
            val (errorType, message, hint, line, column) = it.destructured
            CompilerError(errorType, message.trim(), hint, line.toInt(), column.toInt())
        }.toList()
        if (messages.isEmpty()) {
            LOG.warn("No error messages found in output for compiler version ${info.version}: ${result.stderr}")
        }
        return messages
    }
}

/** Data class to store compiler error information. */
data class CompilerError(
    val errorType: String,
    val message: String,
    val hint: String?,
    val line: Int,
    val column: Int
)
