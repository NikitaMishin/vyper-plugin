package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.impl.DebugUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.vyperlang.plugin.VyperFileType
import org.vyperlang.plugin.VyperInterfaceFileType

class TestColorAnnotator : BasePlatformTestCase() {
    override fun getTestDataPath() = "src/test/resources/TestErrorDetection"

    fun testVySyntax() {
        checkErrors(VyperFileType.INSTANCE, """
                # pragma version ^0.3.0
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
                # pragma version ^0.3.0
                @view
                @public
                def totalSupply() -> uint256:
                    return 0
            """.trimIndent(),
            "<function decorator> expected, got 'public'",
            "<flag declaration>, <import directive>, '@', Identifier, event, implements, interface or struct expected, got 'def'",
        )
    }

    fun testVyiStateVar() {
        checkErrors(
            VyperInterfaceFileType.INSTANCE,
            """
                # pragma version ^0.3.0
                a: public(address)
            """,
            "State variables forbidden in `.vyi` files",
        )
    }

    fun testVyiPass() {
        checkErrors(
            VyperInterfaceFileType.INSTANCE,
            """
                # pragma version ^0.3.0
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
                # pragma version >0.3.0
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
                # pragma version ^0.3.0
                @view
                @external
                def allowance(_owner: address, _spender: address) -> uint256:
                    self.a = _owner
            """.trimIndent(),
            "Statements forbidden in `.vyi` files",
        )
    }

    fun testVyiReturnEllipsis() {
        checkErrors(VyperInterfaceFileType.INSTANCE, """
                # pragma version ^0.3.0
                @view
                @external
                def allowance(_owner: address, _spender: address) -> uint256:
                    return ...
            """.trimIndent()
        )
    }

    fun testModuleReferences() {
        checkErrors(VyperFileType.INSTANCE, """
                # pragma version ^0.4.0
                
                # @dev We import and implement the `IERC20` interface,
                # which is a built-in interface of the Vyper compiler.
                from ethereum.ercs import IERC20
                implements: IERC20
                
                # @dev We import and implement the `IERC20Detailed` interface,
                # which is a built-in interface of the Vyper compiler.
                from ethereum.ercs import IERC20Detailed
                implements: IERC20Detailed
                
                
                # @dev We import and implement the `IERC20Permit`
                # interface, which is written using standard Vyper
                # syntax.
                from ...tokens.interfaces import IERC20Permit
                implements: IERC20Permit
                
                from ..auth import ownable
                uses: ownable
                exports: (
                    ownable.owner,
                    # @notice If you integrate the function `transfer_ownership`
                    # into an ERC-721 or ERC-1155 contract that implements an
                    # `is_minter` role, ensure that the previous owner's minter
                    # role is also removed and the minter role is assigned to the
                    # `new_owner` accordingly.
                    ownable.transfer_ownership,
                    # @notice If you integrate the function `renounce_ownership`
                    # into an ERC-721 or ERC-1155 contract that implements an
                    # `is_minter` role, ensure that the previous owner's minter
                    # role as well as all non-owner minter addresses are also
                    # removed before calling `renounce_ownership`.
                    ownable.renounce_ownership,
                )
            """.trimIndent()
        )
    }

    fun testImportAfterStateVar() {
        checkErrors(VyperFileType.INSTANCE, """
                # pragma version ^0.3.0
                a: uint256
                from vyper.interfaces import ERC20
            """.trimIndent(),
            "Imports must come before global variables",
        )
    }

    fun testFunctionBeforeStateVar() {
        checkErrors(VyperFileType.INSTANCE, """
                # pragma version ^0.3.0
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
        val psiString = DebugUtil.psiToString(myFixture.file, false, true)
        assertSameElements(psiString, messages, expected.toList())
    }
}
