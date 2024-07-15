package org.vyperlang.plugin.usages

import com.intellij.psi.impl.DebugUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.vyperlang.plugin.VyperFileType

class TestFindUsagesProvider : BasePlatformTestCase() {
    fun testConstant() {
        val code = """
            a<caret>b: constant(uint256) = 1
            @external
            def foo() -> uint256:
                return ab
        """
        checkUsages(code, 69 to 71)
    }

    fun testImmutable() {
        val code = """
            a<caret>b: immutable(uint256)
            @deploy
            def __init__() -> uint256:
                ab = 1
        """
        checkUsages(code, 62 to 64)
    }

    fun testFunctionParam() {
        val code = """
            @external
            def foo(a<caret>b: uint256) -> uint256:
                return ab
        """
        checkUsages(code, 54 to 56)
    }

    fun testStateVariable() {
        val code = """
            a<caret>b: uint256
            @external
            def foo() -> uint256:
                return self.ab
        """
        checkUsages(code, 60 to 62)
    }

    fun testSelfCall() {
        val code = """
            @external
            def foo<caret>() -> uint256:
                return self.foo()
        """
        checkUsages(code, 48 to 51)
    }

    fun testStruct() {
        val code = """
            struct Foo:
                ab: uint256
            @external
            def foo() -> Foo:
                return Fo<caret>o({ab: 1})
        """
        checkUsages(code, 51 to 54, 67 to 70)
    }

    fun testStructMemberDict() {
        val code = """
            struct Foo:
                a<caret>b: uint256
            @external
            def foo() -> Foo:
                ab: uint256 = 1
                return Foo({ab: ab})
        """
        checkUsages(code, 92 to 94)
    }

    fun testLocalVarConflict() {
        val code = """
            struct Foo:
                ab: uint256
            @external
            def foo() -> Foo:
                a<caret>b: uint256 = 1
                ab += 1
                return Foo({ab: ab})
        """
        checkUsages(code, 80 to 82, 108 to 110)
    }

    fun testStructMemberKwarg() {
        val code = """
            struct Foo:
                a<caret>b: uint256
            @external
            def foo() -> Foo:
                ab: uint256 = 1
                return Foo(ab=ab)
        """
        checkUsages(code, 91 to 93)
    }

    fun testEnum() {
        val code = """
            flag Action:
                BUY
            @external
            @view
            def buy() -> Act<caret>ion:
                return Action.BUY
        """
        checkUsages(code, 50 to 56, 69 to 75)
    }

    fun testEnumMember() {
        val code = """
            enum Action:
                BUY
            @external
            @view
            def buy() -> Action:
                return Action.BU<caret>Y
        """
        checkUsages(code, 76 to 79)
    }

    fun testLog() {
        val code = """
            event Lo<caret>g:
                test: uint256
            @external
            def foo() -> uint256:
                log Log(1)
        """
        checkUsages(code, 69 to 72)
    }

    fun testImport(){
        val code = """
            from vyper.interfaces import ERC20
            implements: ERC<caret>20
            @external
            def balanceOf(a: address) -> uint256:
                return ERC20(a).balanceOf(self)
        """
        checkUsages(code, 47 to 52, 112 to 117)
    }

    fun testInterface(){
        val code = """
            interface ERC<caret>20:
                def balanceOf(who: address) -> uint256: view
            @external
            def myBalance(coin: address) -> uint256:
                return ERC20(coin).balanceOf(self)
        """
        checkUsages(code, 128 to 133)
    }

    fun testInterfaceMember(){
        val code = """
            interface ERC20:
                def balance<caret>Of(who: address) -> uint256: view
            @external
            def myBalance(coin: address) -> uint256:
                return ERC20(coin).balanceOf(self)
        """
        checkUsages(code, 140 to 149)
    }

    private fun checkUsages(code: String, vararg expectedRanges: Pair<Int, Int>) {
        myFixture.configureByText(VyperFileType.INSTANCE, code.trimIndent().trim())

        val usages = try { myFixture.testFindUsages() } catch (e: AssertionError) { emptyList() }
        val expected = expectedRanges.toList().map { "${it.first}-${it.second}" }
        val actual = usages.map { "${it.navigationRange.startOffset}-${it.navigationRange.endOffset}" }

        val psiString = DebugUtil.psiToString(myFixture.file, false, true)
        assertSameElements(psiString, actual, expected)
    }
}
