package org.vyperlang.plugin.parser

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.vyperlang.plugin.VyperFileType

class TestCompletion : BasePlatformTestCase() {
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
                
                @public
                def foo() -> uint256:
                    x: uint256 = 1
                    y: uint256 = 2
                    z: uint256 = x + <caret>
                    return z
            """.trimIndent(),
            // todo: "a", "b", "x", "y"
        )
    }

    fun testConstantTypeCompletion() {
        checkCompletion(
            "a: constant(<caret>",
            "address", "bool", "bytes32", "bytes[]", "HashMap[]", "int128", "map()", "string[]", "uint256"
        )
    }

    fun testTypeCompletion() {
        checkCompletion(
            "a: <caret>", // todo: should include `immutable` and `constant` too
            "address", "bool", "bytes32", "bytes[]", "HashMap[]", "int128", "map()", "string[]", "uint256"
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
            // todo: "a", "b"
        )
    }

    fun testCompletionInConstant() {
        checkCompletion(
            """
                a: constant(uint256) = 1
                b: constant(uint256) = <caret>
            """.trimIndent(),
            "a", "b"  // todo: b should not be here
        )
    }

    fun testCompletionInDecorator() {
        checkCompletion(
            "@<caret>",
            "public",
            "private",
            "payable",
            "nonreentrant()",
            "modifying",
            "constant",
            "external",
            "view",
            "internal",
            "pure"
        )
    }

    fun testCompleteVar() {
        checkCompletion(
            """
                @public
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

                @public
                def foo():
                    self.<caret>
            """.trimIndent(),
            "balance", "foo()"
        )
    }

    fun testCompleteMsg() {
        checkCompletion(
            """
                @public
                def foo():
                    msg.<caret>
            """.trimIndent(),
            "gas", "sender", "value",
        )
    }

    fun testAutoCompleteMsg() {
        checkCompletion(
            """
                @public
                def foo():
                    msg.g<caret>
            """.trimIndent(),
            "as" // becomes `gas`
        )
    }

    private fun checkCompletion(code: String, vararg expected: String) {
        myFixture.configureByText(VyperFileType.INSTANCE, code)
        myFixture.complete(CompletionType.BASIC)
        if (expected.size == 1) {
            // the editor should automatically pick the only option
            assertEquals(myFixture.editor.document.text, code.replace("<caret>", expected[0]))
            assertNull(myFixture.lookupElementStrings)
        } else {
            val elements = myFixture.lookupElementStrings!!.toMutableList()
            assertSameElements(elements, *expected)
        }
    }
}
