package TestErrorDetection

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.fileTypes.FileType
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.VyperInterfaceFileType

class TestErrorDetection : BasePlatformTestCase() {
    override fun getTestDataPath() = "src/test/resources/TestErrorDetection"

    fun testVySyntax() {
        checkErrors(VyperFileType.INSTANCE, """
                @view
                @external
                def totalSupply() -> uint256:
                    ...
            """.trimIndent(),
            "Ellipsis is only allowed in `.vyi` files",
        )
    }

    fun testOldDecorator() {
        checkErrors(VyperFileType.INSTANCE, """
                @view
                @public
                def totalSupply() -> uint256:
                    return 0
            """.trimIndent(),
            "<function decorator> expected, got 'public'",
            "<import directive>, '@', Identifier, event, from, implements, import, interface or struct expected, got 'def'",
        )
    }

    fun testVyiStateVar() {
        checkErrors(
            VyperInterfaceFileType.INSTANCE,
            "a: public(address)",
            "State variables forbidden in `.vyi` files",
        )
    }

    fun testVyiPass() {
        checkErrors(
            VyperInterfaceFileType.INSTANCE,
            """
                @view
                @external
                def totalSupply() -> uint256:
                    pass
            """.trimIndent(),
            "Statements forbidden in `.vyi` files"
        )
    }

    fun testVyiInternal() {
        checkErrors(
            VyperInterfaceFileType.INSTANCE,
            """
                @view
                @internal
                def balanceOf(_owner: address) -> uint256:
                    ...
            """.trimIndent(),
            "Internal methods forbidden in `.vyi` files",
        )
    }

    fun testVyiStatement() {
        checkErrors(VyperInterfaceFileType.INSTANCE, """
                @view
                @external
                def allowance(_owner: address, _spender: address) -> uint256:
                    self.a = _owner
            """.trimIndent(),
            "Statements forbidden in `.vyi` files",
        )
    }

    fun testImportAfterStateVar() {
        checkErrors(VyperFileType.INSTANCE, """
                a: uint256
                from vyper.interfaces import ERC20
            """.trimIndent(),
            "Imports must come before global variables",
        )
    }

    fun testFunctionBeforeStateVar() {
        checkErrors(VyperFileType.INSTANCE, """
                @external
                def foo() -> uint256:
                    return 0
                a: uint256
            """.trimIndent(),
            "Global variables must all come before function definitions",
        )
    }

    private fun checkErrors(type: FileType, code: String, vararg expected: String) {
        myFixture.configureByText(type, code)
        val messages = myFixture.doHighlighting()
            .filter { it.severity == HighlightSeverity.ERROR }
            .map { it.description }
        assertSameElements(messages, *expected)
    }
}
