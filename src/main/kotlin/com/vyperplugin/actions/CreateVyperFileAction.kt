package com.vyperplugin.actions


import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.vyperplugin.VyperIcons

private const val name = "Create empty Vyper file"
private const val description = "Empty Vyper file"

class CreateVyperFileAction : CreateFileFromTemplateAction(name, description, VyperIcons.FILE) {
//    override fun getActionName(directory: PsiDirectory?, newName: String?, templateName: String?): String = name
//    override fun buildDialog(project: Project?, directory: PsiDirectory?, builder: CreateFileFromTemplateDialog.Builder) {
//        builder.setTitle(name).addKind("Empty file", VyperIcons.FILE, "Vyper File")
//
//    }

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle(name).addKind("Empty file", VyperIcons.FILE, "Vyper File")
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String = name
}