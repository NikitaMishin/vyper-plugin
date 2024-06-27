package org.vyperlang.plugin.parser

import com.intellij.testFramework.ParsingTestCase
import org.vyperlang.plugin.VyperParserDefinition


class TestParse : ParsingTestCase("TestParse", "vy", VyperParserDefinition()) {
    fun testParsingTestData() {
        doTest(true)
    }

    /**
     * @return path to test data file directory relative to root of this module.
     */
    override fun getTestDataPath(): String {
        return "src/test/resources"
    }

    override fun includeRanges(): Boolean {
        return true
    }
}
