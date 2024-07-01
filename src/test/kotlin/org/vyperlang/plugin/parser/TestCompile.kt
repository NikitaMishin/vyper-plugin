package org.vyperlang.plugin.parser

import com.intellij.mock.MockProgressIndicator
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.wm.RegisterToolWindowTask
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Assert.assertTrue
import org.vyperlang.plugin.VyperIcons
import org.vyperlang.plugin.compile.VyperCompiler
import org.vyperlang.plugin.compile.VyperParameters
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.ToolResult
import org.vyperlang.plugin.settings.VyperSettings
import org.vyperlang.plugin.toolWindow.VyperWindow

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
        val result = compile("example.vy")
        assertEmpty(result.stderr)
        assertMatches(result.stdout.joinToString(""), "^0x([0-9a-f]{2})+$".toRegex())
        assertEquals(result.statusDocker, StatusDocker.SUCCESS)
    }

    private fun compile(fileName: String): ToolResult {
        val file = LocalFileSystem.getInstance().refreshAndFindFileByPath("${testDataPath}/${fileName}")
        val files = listOf(file!!)
        val module = ModuleManager.getInstance(project).modules.first()
        val settings = VyperSettings.INSTANCE
        val params = VyperParameters(module, project, files, settings.getCompilerParamsArray(), settings.generateStubs, settings.fileExtension)
        val indicator = MockProgressIndicator()
        return VyperCompiler.compile(params, indicator)[0]!!
    }
}

fun assertMatches(output: String, regex: Regex) =
    assertTrue("Output does not match regex: $regex for output: $output", regex.containsMatchIn(output))
