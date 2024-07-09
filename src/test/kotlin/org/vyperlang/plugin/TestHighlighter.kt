package org.vyperlang.plugin

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@TestDataPath("\$CONTENT_ROOT/src/examples")
@RunWith(Parameterized::class)
class TestHighlighter(private val file: String) :
    BasePlatformTestCase() {
    override fun getTestDataPath() = "src/test/resources/examples"

    companion object {
        @JvmStatic
        @Parameters(name = "{0}")
        fun data(): Array<Array<Any>> {
            return arrayOf(
                arrayOf("example.vy"),
                arrayOf("test.vy"),
                arrayOf("backslash.vy"),
                arrayOf("f/ff/fff.vy"),
                arrayOf("IERC20.vyi"),
                arrayOf("CurveStableSwapNG.vy"),
            )
        }
    }

    @Test
    fun testGrammar() {
        myFixture.configureByFile(file)
        myFixture.checkHighlighting()
    }
}
