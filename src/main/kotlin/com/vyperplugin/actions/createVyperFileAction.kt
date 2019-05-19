package com.vyperplugin.actions


import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.vyperplugin.VyperIcons

private val name = "Create empty Vyper file"
private val description = "Empty Vyper file"

class createVyperFileAction : CreateFileFromTemplateAction(name, description, VyperIcons.FILE) {

    override fun getActionName(directory: PsiDirectory?, newName: String?, templateName: String?): String = name
    override fun buildDialog(project: Project?, directory: PsiDirectory?, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle(name).addKind("Empty file", VyperIcons.FILE, "Vyper File")

    }
}