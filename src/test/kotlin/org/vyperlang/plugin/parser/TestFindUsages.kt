package org.vyperlang.plugin.parser

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.vyperlang.plugin.VyperFileType


class TestFindUsages : BasePlatformTestCase() {
    fun testConstant() {
        val code = """
            a<caret>b: constant(uint256) = 1
            @public
            def foo() -> uint256:
                return ab
        """
        checkUsages(code, 67 to 69) // todo: should include the declaration itself
    }

    fun testImmutable() {
        val code = """
            a<caret>b: immutable(uint256)
            @deploy
            def __init__() -> uint256:
                ab = 1
        """
        checkUsages(code) // todo: immutables are not yet supported
    }

    fun testFunctionParam() {
        val code = """
            @public
            def foo(a<caret>b: uint256) -> uint256:
                return ab
        """
        checkUsages(code, 52 to 54) // todo: should include the declaration itself
    }

    fun testStateVariable() {
        val code = """
            a<caret>b: uint256
            @public
            def foo() -> uint256:
                return self.ab
        """
        checkUsages(code, 58 to 60) // todo: should include the declaration itself
    }

    fun testSelfCall() {
        val code = """
            @public
            def foo<caret>() -> uint256:
                return self.foo()
        """
        checkUsages(code, 46 to 49, 41 to 51)
    }

    fun testStruct() {
        val code = """
            struct Foo:
                ab: uint256
            @public
            def foo() -> Foo:
                return Fo<caret>o({ab: 1})
        """
        checkUsages(code)
    }

    fun testStructMember() {
        val code = """
            struct Foo:
                a<caret>b: uint256
            @public
            def foo() -> Foo:
                return Foo(ab=1)
        """
        checkUsages(code) // todo: should find the usage
    }

    fun testLog() {
        val code = """
            event Lo<caret>g:
                test: uint256
            @public
            def foo() -> uint256:
                log Log(1)
        """
        checkUsages(code)
    }

    fun testImport(){
        val code = """
            from vyper.interfaces import ERC20
            implements: ERC<caret>20
            @public
            def balanceOf(a: address) -> uint256:
                return ERC20(a).balanceOf(self)
        """
        checkUsages(code)
    }

    private fun checkUsages(code: String, vararg expectedRanges: Pair<Int, Int>) {
        myFixture.configureByText(VyperFileType.INSTANCE, code.trimIndent().trim())
        val usages = try { myFixture.testFindUsages() } catch (e: AssertionError) { emptyList() }
        val expected = expectedRanges.toList().map { "${it.first}-${it.second}" }
        val actual = usages.map { "${it.navigationRange.startOffset}-${it.navigationRange.endOffset}" }
        assertSameElements(actual, expected)
    }
}
