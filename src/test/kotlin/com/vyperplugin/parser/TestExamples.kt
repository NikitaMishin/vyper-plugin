package com.vyperplugin.parser

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.vyperplugin.psi.VyperElement
import com.vyperplugin.psi.childOfType

@TestDataPath("\$CONTENT_ROOT/src/examples")
class TestExamples : BasePlatformTestCase() {
    override fun getTestDataPath() = "src/examples"

    fun testCurveStableSwapNG() {
        myFixture.testHighlighting("CurveStableSwapNG.vy")
    }

    fun testExampleVy() {
        myFixture.testHighlighting("example.vy")
    }

    fun testTestVy() {
        myFixture.testHighlighting("test.vy")
    }

    fun testBackslash() {
        myFixture.testHighlighting("backslash.vy")
    }

}
