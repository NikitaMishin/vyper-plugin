package org.vyperlang.plugin.parser

import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.RegisterToolWindowTask
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.vyperlang.plugin.VyperIcons
import org.vyperlang.plugin.annotators.VyperCompilerListener
import org.vyperlang.plugin.compile.VyperCompiler
import org.vyperlang.plugin.compile.VyperParameters
import org.vyperlang.plugin.settings.VyperSettings
import org.vyperlang.plugin.toolWindow.VyperWindow
import java.io.File
import java.util.concurrent.CountDownLatch

val lock = CountDownLatch(10)

class TestCompile : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/resources/examples"
    }

    @Suppress("UnstableApiUsage") // https://intellij-support.jetbrains.com/hc/en-us/community/posts/7684067077650/comments/7713173276050
    override fun setUp() {
        super.setUp()
        ToolWindowManager.getInstance(project).registerToolWindow(
            RegisterToolWindowTask.closable(VyperWindow.ID_TOOL_WINDOW, VyperIcons.FILE) // , true, ToolWindowAnchor.RIGHT, project, true)
        )
    }

    fun testCompile() {
        val output = compile("example.vy")
        assertEquals("File \"<unknown>\", line 13\n" +
                "event AuctionEnded:\n" +
                "^\n" +
                "SyntaxError: invalid syntax", output)
    }

    private fun compile(fileName: String): String? {
        val file = LocalFileSystem.getInstance().refreshAndFindFileByPath("${testDataPath}/${fileName}")
        val files = arrayOf(file!!)
        val module = ModuleManager.getInstance(project).modules.first()
        val settings = VyperSettings.INSTANCE
        VyperCompiler.compile(
            VyperParameters(
                module,
                project,
                files,
                settings.getCompilerParamsArray(),
                settings.generateStubs,
                settings.fileExtension
            )
        )
        return VyperCompiler.output
    }

    private val windowText: String get() {
        val document = VyperWindow.getTabById(project, VyperWindow.ID_COMPILER_TAB).document
        return document.getText(0, document.length)
    }
}
