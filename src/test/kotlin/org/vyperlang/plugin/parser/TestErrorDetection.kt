package org.vyperlang.plugin.parser

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase

@TestDataPath("\$CONTENT_ROOT/src/examples")
class TestErrorDetection : BasePlatformTestCase() {
    override fun getTestDataPath() = "src/test/resources/examples"

    fun testVySyntax() {
        val messages = findErrors("incorrect.vy")
        assertEquals(messages.joinToString("\n"), listOf(
            "...: Ellipsis is only allowed in `.vyi` files",
        ).joinToString("\n"))
    }

    fun testVyiSyntax() {
        val messages = findErrors("incorrect.vyi")
        assertEquals(messages.joinToString("\n"), listOf(
            "a: public(address): State variables forbidden in `.vyi` files",
            "pass: Statements forbidden in `.vyi` files",
            "internal: Internal methods forbidden in `.vyi` files",
            "self.a = _owner: Statements forbidden in `.vyi` files",
        ).joinToString("\n"))
    }

    private fun findErrors(filePath: String): List<String> {
        myFixture.configureByFile(filePath)
        val messages = myFixture.doHighlighting().filter { it.severity == HighlightSeverity.ERROR }
            .map { it.text + ": " + it.description }
        return messages
    }
}
