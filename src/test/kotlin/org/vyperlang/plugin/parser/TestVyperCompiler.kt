package org.vyperlang.plugin.parser

import com.intellij.mock.MockProgressIndicator
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.wm.RegisterToolWindowTask
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.vyperlang.plugin.VyperIcons
import org.vyperlang.plugin.annotators.VYPER_ERROR_REGEX
import org.vyperlang.plugin.compile.VyperCompiler
import org.vyperlang.plugin.compile.VyperParameters
import org.vyperlang.plugin.docker.CompilerMissingError
import org.vyperlang.plugin.docker.StatusDocker
import org.vyperlang.plugin.docker.ToolResult
import org.vyperlang.plugin.settings.VyperSettings
import org.vyperlang.plugin.toolWindow.VyperWindow

class TestVyperCompiler : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/resources"
    }

    @Suppress("UnstableApiUsage") // https://intellij-support.jetbrains.com/hc/en-us/community/posts/7684067077650/comments/7713173276050
    override fun setUp() {
        super.setUp()
        ToolWindowManager.getInstance(project).registerToolWindow(
            RegisterToolWindowTask.closable(VyperWindow.ID_TOOL_WINDOW, VyperIcons.FILE) // , true, ToolWindowAnchor.RIGHT, project, true)
        )
    }

    fun testMissingDocker() {
        val params = mockk<VyperParameters> {
            every { files } throws CompilerMissingError(Exception("Docker is not installed"))
            every { project } returns myFixture.project
        }
        assertNull(VyperCompiler.compile(params))
    }

    fun testCompile() {
        val result = compile("examples/example.vy")
        assertEmpty(result.stderr)
        assertMatches(result.stdout, "^0x([0-9a-f]{2})+$".toRegex())
        assertEquals(result.statusDocker, StatusDocker.SUCCESS)
    }

    fun testCompileError() {
        val result = compile("TestCompilerAnnotator/syntax-exception.vy")
        assertEmpty(result.stdout)
        assertMatches(result.stderr, VYPER_ERROR_REGEX)
        assertEquals(result.statusDocker, StatusDocker.FAILED)
    }

    private fun compile(fileName: String): ToolResult {
        val file = LocalFileSystem.getInstance().findFileByPath("${testDataPath}/${fileName}")
        val files = listOf(file!!)
        val module = ModuleManager.getInstance(project).modules.first()
        val settings = VyperSettings.INSTANCE
        val params = VyperParameters(module, project, files, settings.getCompilerParamsArray(), settings.generateStubs, settings.fileExtension)
        val indicator = MockProgressIndicator()
        return VyperCompiler.compile(params, indicator)!![0]
    }
}

fun assertMatches(output: String, regex: Regex) =
    assertTrue("Output does not match regex: $regex for output: $output", regex.containsMatchIn(output))
