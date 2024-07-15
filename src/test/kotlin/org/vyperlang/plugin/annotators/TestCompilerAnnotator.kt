package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import io.mockk.checkUnnecessaryStub
import io.mockk.every
import io.mockk.mockk
import org.vyperlang.plugin.psi.file

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
        ExpectedError("Function should not return any values", 23 to 29, "FunctionDeclarationException"),
    )

    fun testOverflowException() = testCase(
        "overflow-exception.vy",
        ExpectedError(
            "Numeric literal is outside of allowable range for number types",
            62 to 63, "OverflowException"
        ),
    )

    fun testModuleNotFound() = testCase(
        "module-not-found.vy",
        ExpectedError("Unknown interface: foobar. Did you mean 'ERC20Detailed', or maybe 'ERC165'?", 24 to 28, "UndeclaredDefinition"),
    )

    fun testInvalidType() = testCase(
        "invalid-type.vy",
        ExpectedError("Expected uint256 but literal can only be cast as bool.", 50 to 55, "TypeMismatch"),
    )

    fun testStructureException() = testCase(
        "structure-exception.vy",
        ExpectedError("Second value must be > first value", 65 to 66, "StructureException"),
    )

    fun testIteratorException() = testCase(
        "iterator-error.vy",
        ExpectedError("Cannot iterate over the result of a function call", 58 to 61, "IteratorException"),
    )

    fun testVy3() = testCase(
        "test-vy3.vy",
        ExpectedError("Unknown decorator: deploy", 92 to 98, "FunctionDeclarationException"),
    )

    fun testVy4() = testCase(
        "test-vy4.vy",
        ExpectedError("Immutable variables must be accessed without 'self'", 67 to 68, "ImmutableViolation"),
        ExpectedError("Constructor must be marked as `@deploy`", 100 to 103, "FunctionDeclarationException"),
    )

    fun testOK() = testCase("ok.vy")

    private fun testCase(fileName: String, vararg expectedErrors: ExpectedError) {
        myFixture.configureByFile(fileName)
        val info = fileInfo()
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
        CompilerAnnotator().apply(myFixture.file, errors, holder)
        checkUnnecessaryStub(holder)
    }

    // we don't call annotator.collectInformation() directly, because the fixture gets a temp:// path
    private fun fileInfo(): FileInfo {
        val file = LocalFileSystem.getInstance().findFileByPath("${testDataPath}/${myFixture.file.name}")!!
        return FileInfo(project, file, myFixture.file.file.vyperVersion, mockk<ProgressIndicator>(relaxed = true))
    }
}
