package org.vyperlang.plugin.parser

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@TestDataPath("\$CONTENT_ROOT/src/examples")
@RunWith(Parameterized::class)
class TestExamplesHighlighting(private val file: String, private val expectedWarnings: List<String>) :
    BasePlatformTestCase() {
    override fun getTestDataPath() = "src/test/resources/examples"

    companion object {
        @JvmStatic
        @Parameters(name = "{0}")
        fun data(): Array<Array<Any>> {
            val noWarnings = listOf<String>()
            return arrayOf(
                arrayOf("example.vy", noWarnings),
                arrayOf("test.vy", noWarnings),
                arrayOf("backslash.vy", noWarnings),
                arrayOf("f/ff/fff.vy", noWarnings),
                arrayOf("IERC20.vyi", noWarnings),
                arrayOf("CurveStableSwapNG.vy", listOf("Entrancy key is deprecated")),
            )
        }
    }

    @Test
    fun testGrammar() {
        myFixture.configureByFile(file)
        val issues = myFixture.doHighlighting()
            .filter { it.severity != HighlightSeverity.INFORMATION }

        assertEmpty(issues
            .filter { it.severity != HighlightSeverity.WARNING || !expectedWarnings.contains(it.description) }
            .map { it.description })

        if (expectedWarnings.isNotEmpty()) {
            assertSameElements(issues
                .filter { it.severity == HighlightSeverity.WARNING }
                .map { it.description }, expectedWarnings)
        }
    }
}
