package com.vyperplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.vfs.VirtualFile

abstract class VyperAction : AnAction() {
    val vyExtensionRegExp: Regex = Regex(".+\\.vy$")

    protected fun getClickedFiles(e: AnActionEvent): Array<VirtualFile>? =
            PlatformDataKeys.VIRTUAL_FILE_ARRAY.getData(e.dataContext)

}