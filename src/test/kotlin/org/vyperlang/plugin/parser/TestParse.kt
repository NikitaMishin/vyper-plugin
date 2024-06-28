package org.vyperlang.plugin.parser

import com.intellij.testFramework.ParsingTestCase
import org.vyperlang.plugin.VyperParserDefinition

private const val checkResult = true
private const val ensureNoErrorElements = true

/**
 * Test that parses the file and compares it to a PSI snapshot.
 */
class TestParse : ParsingTestCase("TestParse", "vy", VyperParserDefinition()) {
    fun testParsingTestData() = doTest(checkResult, ensureNoErrorElements)
    override fun getTestDataPath(): String = "src/test/resources"
    override fun includeRanges(): Boolean = checkResult
}
