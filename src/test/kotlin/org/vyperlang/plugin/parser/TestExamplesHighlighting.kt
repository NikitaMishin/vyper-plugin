package org.vyperlang.plugin.parser

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@TestDataPath("\$CONTENT_ROOT/src/examples")
@RunWith(Parameterized::class)
class TestExamplesHighlighting(private val file: String) : BasePlatformTestCase() {
    override fun getTestDataPath() = "src/test/resources/examples"

    companion object {
        @JvmStatic
        @Parameters(name = "{0}")
        fun data(): Array<String> {
            return arrayOf("example.vy", "test.vy", "backslash.vy", "f/ff/fff.vy", "IERC20.vyi", "CurveStableSwapNG.vy")
        }
    }

    @Test
    fun testGrammar() {
        myFixture.testHighlighting(file)
    }
}
