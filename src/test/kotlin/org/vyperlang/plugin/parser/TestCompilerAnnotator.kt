package org.vyperlang.plugin.parser

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import io.mockk.checkUnnecessaryStub
import io.mockk.every
import io.mockk.mockk
import org.vyperlang.plugin.annotators.CompilerAnnotator
import org.vyperlang.plugin.annotators.CompilerError
import org.vyperlang.plugin.annotators.FileInfo

data class ExpectedError(val message: String, val range: Pair<Int, Int>, val tooltip: String="")

class TestCompilerAnnotator : BasePlatformTestCase() {
    override fun getTestDataPath(): String {
        return "src/test/resources/TestCompilerAnnotator"
    }

    fun testSyntaxException() = testCase(
        "syntax-exception.vy",
        ExpectedError("expected '(' (<unknown>, line 1)", 4 to 5, "SyntaxException"),
    )

    fun testUndeclaredDefinition() = testCase(
        "undeclared-definition.vy",
        ExpectedError("Storage variable 'y' has not been declared.", 41 to 45, "UndeclaredDefinition"),
    )

    fun testFunctionDeclarationException() = testCase(
        "function-declaration-exception.vy",
        ExpectedError("Function does not return any values", 23 to 29, "FunctionDeclarationException"),
    )

    fun testOverflowException() = testCase(
        "overflow-exception.vy",
        ExpectedError(
            "Result of exponentiation (115792089237316195423570985008687907853269984665640564039457584007913129639936)" +
                    " is outside bounds of all numeric types",
            62 to 63, "OverflowException"
        ),
    )

    fun testModuleNotFound() = testCase(
        "module-not-found.vy",
        ExpectedError("Unknown interface: foobar. Did you mean 'ERC20Detailed', or maybe 'ERC165'?", 0 to 4, "UndeclaredDefinition"),
    )

    fun testInvalidType() = testCase(
        "invalid-type.vy",
        ExpectedError("Expected uint256 but literal can only be cast as bool.", 50 to 55, "InvalidType"),
    )

    fun testStructureException() = testCase(
        "structure-exception.vy",
        ExpectedError("Second value must be > first value", 41 to 42, "StructureException"),
    )

    fun testIteratorException() = testCase(
        "iterator-error.vy",
        ExpectedError("Cannot iterate over the result of a function call", 34 to 37, "IteratorException"),
    )

    fun testOK() = testCase("ok.vy")

    private fun testCase(fileName: String, vararg expectedErrors: ExpectedError) {
        val info = fileInfo(fileName)
        val errors = CompilerAnnotator().doAnnotate(info)
        assertSize(expectedErrors.size, errors)
        annotate(fileName, errors, *expectedErrors)
    }

    private fun annotate(fileName: String, errors: List<CompilerError>, vararg expected: ExpectedError) {
        val holder = mockk<AnnotationHolder> {
            expected.forEach {
                every { newAnnotation(HighlightSeverity.ERROR, it.message) } returns mockk {
                    every { range(TextRange(it.range.first, it.range.second)) } returns this
                    every { tooltip("${it.message} (${it.tooltip})") } returns this
                    every { create() } returns mockk()
                }
            }
        }
        myFixture.configureByFile(fileName)
        CompilerAnnotator().apply(myFixture.file, errors, holder)
        checkUnnecessaryStub(holder)
    }

    private fun fileInfo(fileName: String): FileInfo {
        val file = LocalFileSystem.getInstance().findFileByPath("${testDataPath}/$fileName")!!
        return FileInfo(project, file, mockk(relaxed = true))
    }
}
