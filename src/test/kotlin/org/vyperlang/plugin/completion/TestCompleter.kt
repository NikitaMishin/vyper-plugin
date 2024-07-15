package org.vyperlang.plugin.completion

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.vyperlang.plugin.VyperFileType

class TestCompleter : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/resources/examples"
    }

    fun testCompletionInitial() {
        checkCompletion("<caret>", "interface", "def", "import", "struct")
    }

    fun testCompletionInFunction() {
        checkCompletion(
            """
                a: constant(uint256) = 1
                b: immutable(uint256)
                
                @deploy
                def __init__():
                    b = 1
                
                @external
                def foo() -> uint256:
                    x: uint256 = 1
                    y: uint256 = 2
                    z: uint256 = x + <caret>
                    return z
            """.trimIndent(),
            "a", "b", "x", "y"
        )
    }

    fun testConstantTypeCompletion() {
        checkCompletion(
            "a: constant(<caret>",
            "address", "bool", "bytes32", "bytes[", "int128", "string[", "uint256"
        )
    }

    fun testImmutableTypeCompletion() {
        checkCompletion(
            "a: public(immutable(<caret>",
            "address", "bool", "bytes32", "bytes[", "int128", "string[", "uint256"
        )
    }

    fun testTypeCompletion() {
        checkCompletion(
            "a: <caret>",
            "address", "bool", "bytes32", "bytes[", "constant(", "DynArray[]", "HashMap[]", "immutable(", "int128", "public(", "string[", "uint256"
        )
    }

    fun testCompletionInConstructor() {
        checkCompletion(
            """
                a: constant(uint256) = 1
                b: immutable(uint256)

                @deploy
                def __init__():
                    b = 1
                    <caret>
            """.trimIndent(),
            "a", "b"
        )
    }

    fun testCompletionInConstant() {
        checkCompletion(
            """
                a: constant(uint256) = 1
                b: constant(uint256) = <caret>
            """.trimIndent(),
            "a"
        )
    }

    fun testCompletionInDecorator() {
        checkCompletion(
            "@<caret>",
            "deploy", "external", "internal", "nonreentrant(\"lock\")", "payable", "pure", "view",
        )
    }

    fun testCompleteVar() {
        checkAutoCompletion(
            """
                @external
                def foo():
                    abc: uint256 = 1
                    x: uint256 = a<caret>
            """.trimIndent(),
            "bc" // becomes `abc`
        )
    }

    fun testCompleteSelf() {
        checkCompletion(
            """
                balance: uint256

                @external
                def foo():
                    self.<caret>
            """.trimIndent(),
            "balance", "foo()"
        )
    }

    fun testCompleteMsg() {
        checkCompletion(
            """
                @external
                def foo():
                    msg.<caret>
            """.trimIndent(),
            "gas", "sender", "value",
        )
    }

    fun testAutoCompleteMsg() {
        checkAutoCompletion(
            """
                @external
                def foo():
                    msg.g<caret>
            """.trimIndent(),
            "as" // becomes `gas`
        )
    }

    private fun checkCompletion(code: String, vararg expected: String) {
        myFixture.configureByText(VyperFileType.INSTANCE, code)
        myFixture.complete(CompletionType.BASIC)
        val elements = myFixture.lookupElementStrings!!.toMutableList()
        assertSameElements(elements, *expected)
    }

    private fun checkAutoCompletion(code: String, expected: String) {
        myFixture.configureByText(VyperFileType.INSTANCE, code)
        myFixture.complete(CompletionType.BASIC)
        assertNull(myFixture.lookupElementStrings)
        assertEquals(myFixture.editor.document.text, code.replace("<caret>", expected))
    }
}