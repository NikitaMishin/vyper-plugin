package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import org.vyperlang.plugin.VyperFileType

private const val Vy3 = "#pragma version 0.3.10"
private const val Vy4 = "#pragma version 0.4.0"

@RunWith(Parameterized::class)
class TestVersionAnnotator(private val case: TestCase) : BasePlatformTestCase() {

    data class TestCase(val name: String, val code: String, val pragma: String, val expectedErrors: List<String> = emptyList()) {
        override fun toString() = "$name: ${pragma.ifEmpty {"non specified"}}"

        val formattedCode get() = code.replace("{pragma}", pragma).trimIndent()

        companion object {
            fun create(name: String, code: String, vararg cases: Pair<String, List<String>>) =
                cases.map { (pragma, errors) -> TestCase(name, code, pragma, errors) }
        }
    }

    companion object {

        @JvmStatic
        @Parameters(name = "{0}")
        fun data(): Array<TestCase> = listOf(
            TestCase.create(
                "extcall",
                """
                    {pragma}
                    @external
                    def foo() -> uint256:
                        extcall self.foo()
                """,
                "# pragma version" to listOf(VYPER_VERSION_NOT_SPECIFIED),
                "# pragma version ^0.3.10" to listOf(EXTCALL_NOT_VY3),
                "# pragma version ^0.4.0" to emptyList(),
            ),
            TestCase.create(
                "staticcall",
                """
                    {pragma}
                    x: address
                    @external
                    def foo() -> uint256:
                        staticcall self.x.foo()
                """,
                "#@version" to listOf(VYPER_VERSION_NOT_SPECIFIED),
                "#@version 0.3.9" to listOf(STATICCALL_NOT_VY3),
                "#@version ^0.4.0" to emptyList(),
            ),
            TestCase.create(
                "nonreentrant",
                """
                    {pragma}
                    @external
                    @nonreentrant('lock')
                    def foo() -> uint256:
                        return 0
                """,
                "" to listOf(VYPER_VERSION_NOT_SPECIFIED),
                "#pragma version ^0.3.9" to emptyList(),
                "#pragma version ^0.4.0" to listOf(NAMED_LOCKS_NOT_V4),
            ),
            TestCase.create(
                "require extcall",
                """
                    {pragma}
                    interface Foo:
                        def foo(x: decimal) -> decimal: payable
                    @external
                    def foo(a: Foo) -> int256:
                        return ceil(a.foo(2.5))
                """,
                "" to listOf(VYPER_VERSION_NOT_SPECIFIED),
                Vy3 to emptyList(),
                "#pragma version 0.4.0" to listOf(MISSING_EXTCALL),
            ),
            TestCase.create(
                "require staticcall",
                """
                    {pragma}
                    a: Foo
                    interface Foo:
                        def foo(x: uint256) -> uint256: pure
                    @external
                    def foo() -> uint256:
                        return self.a.foo(2)
                """,
                "" to listOf(VYPER_VERSION_NOT_SPECIFIED),
                "#pragma version ^0.3.10" to emptyList(),
                "#pragma version 0.4.0" to listOf(MISSING_STATICCALL),
            ),

            TestCase.create(
                "struct dict",
                """
                    {pragma}
                    struct Foo:
                        ab: uint256
                    @external
                    def foo() -> Foo:
                        return Foo({ab: 1})
                """,
                Vy3 to emptyList(),
                Vy4 to listOf(STRUCT_DICT_WARN_V4),
            ),
            TestCase.create(
                "struct kwargs",
                """
                    {pragma}
                    struct Foo:
                        ab: uint256
                    @external
                    def foo() -> Foo:
                        return Foo(ab=1)
                """,
                Vy3 to listOf(STRUCT_DICT_NOT_VY3),
                Vy4 to emptyList(),
            ),
            TestCase.create(
                "range with type",
                """
                    {pragma}
                    @external
                    def write_junk_to_memory():
                        xs: int128[1024] = empty(int128[1024])
                        for i: uint256 in range(1024):
                            xs[i] = -(i + 1)
                """,
                Vy3 to listOf(RANGE_TYPE_NOT_V3),
                Vy4 to emptyList(),
            ),
            TestCase.create(
                "range without type",
                """
                    {pragma}
                    @external
                    def write_junk_to_memory():
                        xs: int128[1024] = empty(int128[1024])
                        for i in range(1024):
                            xs[i] = -(i + 1)
                """,
                Vy3 to emptyList(),
                Vy4 to listOf(RANGE_TYPE_REQUIRED_V4),
            ),
            TestCase.create(
                "enum",
                """
                    {pragma}
                    enum Action:
                        BUY
                    @external
                    @view
                    def buy() -> Action:
                        return Action.BUY
                """,
                Vy3 to emptyList(),
                Vy4 to listOf(ENUM_NOT_V4),
            ),
            TestCase.create(
                "flags",
                """
                    {pragma}
                    flag Action:
                        BUY
                    @external
                    @view
                    def buy() -> Action:
                        return Action.BUY
                """,
                Vy3 to listOf(FLAGS_NOT_V3),
                Vy4 to emptyList(),
            ),
            TestCase.create(
                "range with bound",
                """
                    {pragma}
                    @external
                    def repeat(n: uint256) -> uint256:
                        x: uint256 = 0
                        for i: uint256 in range(n, 7, bound=6):
                            x += i + 1
                        return x
                """,
                Vy3 to listOf(RANGE_BOUND_NOT_V3, RANGE_TYPE_NOT_V3),
                Vy4 to emptyList()
            ),
        ).flatten().toTypedArray()
    }

    @Test
    fun test() {
        myFixture.configureByText(VyperFileType.INSTANCE, case.formattedCode)
        if (case.expectedErrors.isEmpty()) {
            myFixture.checkHighlighting()
            return
        }
        val messages = myFixture.doHighlighting()
            .filter { it.severity in listOf(HighlightSeverity.ERROR, HighlightSeverity.WARNING) }
            .map { it.description }
        assertSameElements(messages, *case.expectedErrors.toTypedArray())
    }
}
