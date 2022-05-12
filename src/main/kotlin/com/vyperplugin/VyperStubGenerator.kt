package com.vyperplugin

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.vyperplugin.settings.VyperSettings
import java.io.File


object VyperStubGenerator {

    fun createStubInGenSourceFolder(
        data: String, module: Module, project: Project,
        fullPathToFile: String, extension: String
    ) {
        generateFolder(module, project)
        VirtualFileManager.getInstance().asyncRefresh {
            WriteCommandAction.runWriteCommandAction(project) {

                val parent = getGeneratedSourceRoot(module)
                    ?: throw NullPointerException("No generated source folder is found")
                val filename = fullPathToFile.split("/").last()

                val filePath = parent.path + "/" + filename + extension
                var file = File(filePath)

                VirtualFileManager.getInstance().syncRefresh()
                val isFileExists = file.createNewFile()

                file = File(filePath) // update because need refresh
                val virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(file)!!
                val document: Document = FileDocumentManager.getInstance().getDocument(virtualFile)!!

                if (isFileExists) {
                    document.setText(data)
                } else {
                    document.replaceString(0, document.textLength, data)
                }

            }
        }
    }

    private fun getGeneratedSourceRoot(module: Module): VirtualFile? =
        ModuleRootManager.getInstance(module).sourceRoots
            .firstOrNull { it.name == VyperSettings.INSTANCE.genarateOutputPath }


    private fun generateFolder(module: Module, project: Project) {
        WriteCommandAction.runWriteCommandAction(project) {

            val model = ModuleRootManager.getInstance(module).modifiableModel

            val parent = model.contentEntries.first() ?: model.addContentEntry(project.basePath!!)
            val genDirectory = VfsUtil.createDirectoryIfMissing(parent.file, VyperSettings.INSTANCE.genarateOutputPath)

            // mark as source directory or better exclude folder?
            if (parent.sourceFolderFiles.none { it.path == genDirectory.path })
                parent.addSourceFolder(genDirectory, false)
            genDirectory.refresh(false, false)
            model.commit()
        }
    }

}
