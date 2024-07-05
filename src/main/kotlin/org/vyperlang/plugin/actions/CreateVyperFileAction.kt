package org.vyperlang.plugin.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import org.vyperlang.plugin.VyperIcons

private const val name = "Vyper contract"
private const val description = "Empty Vyper file"
private const val kind = "Empty file"
private const val templateName = "Vyper File"

class CreateVyperFileAction : CreateFileFromTemplateAction(name, description, VyperIcons.FILE) {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle(name).addKind(kind, VyperIcons.FILE, templateName)
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String = name
}
