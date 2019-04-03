package com.vyperplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.vfs.VirtualFile
import java.nio.file.Path
import java.nio.file.Paths

class SmartCheckAnalyze(private val vyExtensionRegExp: Regex = Regex(".+\\.vy$")) : AnAction() {


    override fun actionPerformed(e: AnActionEvent) = this.performAction(e)

    private fun performAction(e: AnActionEvent) {
        val files: Array<VirtualFile>? = getClickedFiles(e)?.filter { it.path.contains(vyExtensionRegExp) }?.toTypedArray()

        if (files == null || files.isEmpty()) {
            e.presentation.isEnabled = false
            e.presentation.isVisible = false
            // show notification that no files are selected with vy extension or no files at all
            return
        }

        val pathsToFilesToAnalyze: Array<Path> = files.map { Paths.get(it.path) }.toTypedArray()
        // user called manually analyzer,so synchronous call
        pathsToFilesToAnalyze
                .map { filename -> startAnalyze(filename) }

        val arr :ArrayList<String> = ArrayList()
        arr.add("-p")
        arr.add(files[0].path)
        ru.smartdec.smartcheck.app.cli.Tool.main(arr.toTypedArray())
    }


    private fun getClickedFiles(e: AnActionEvent): Array<VirtualFile>? =
            PlatformDataKeys.VIRTUAL_FILE_ARRAY.getData(e.dataContext)

    private fun startAnalyze(filename: Path): Boolean {
//        var smartCheckToolWrapper: SmartCheckToolWrapper = SmartCheckToolWrapper.getInstance(filename)
//        smartCheckToolWrapper.performAnalyze()
//        smartCheckToolWrapper.analyzedInfo
//        smartCheckToolWrapper.result
//        return smartCheckToolWrapper.isFileAnalyzed
        return true
    }

    private fun createFormWithAnalyzerOutput(result: Array<String>) {

    }
}




