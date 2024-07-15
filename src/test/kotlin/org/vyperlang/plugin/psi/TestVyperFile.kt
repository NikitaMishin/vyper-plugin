package org.vyperlang.plugin.psi

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.vyperlang.plugin.VyperFileType

val cases = arrayOf(
    "#pragma version 0.3.0",
    "# pragma version ^0.3.0",
    "# @version 0.3.0",
)

@RunWith(Parameterized::class)
class TestVyperFile(private val code: String) : BasePlatformTestCase() {

    companion object {
        @JvmStatic
        @Parameters(name = "{0}")
        fun data() = cases
    }

    @Test()
    fun testVyperVersion() {
        val file = myFixture.configureByText(VyperFileType.INSTANCE, code)
        val vyperVersion = (file as VyperFile).vyperVersion
        assertEquals("0.3.0", vyperVersion?.toString())
    }
}