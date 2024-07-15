package org.vyperlang.plugin.annotators

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import org.vyperlang.plugin.psi.VyperFile
import org.vyperlang.plugin.psi.file

class AddVersionPragmaFix(private val version: String) : IntentionAction {
    override fun startInWriteAction() = true

    override fun getFamilyName() = "Vyper"

    override fun getText() = "Add version pragma $version"

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?) =
        file?.containingFile is VyperFile && file.file.vyperVersion == null

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        editor?.document?.insertString(0, "# pragma version $version\n")
    }
}
