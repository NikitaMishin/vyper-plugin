package com.vyperplugin.annotators

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor

enum class VyperColor(humanName: String, val default: TextAttributesKey) {
    LINE_COMMENT("Comment", DefaultLanguageHighlighterColors.LINE_COMMENT),

    BRACES("Braces", DefaultLanguageHighlighterColors.BRACES),
    BRACKETS("Brackets", DefaultLanguageHighlighterColors.BRACKETS),
    PARENTHESES("Parentheses", DefaultLanguageHighlighterColors.PARENTHESES),
    SEMICOLON("Semicolon", DefaultLanguageHighlighterColors.SEMICOLON),

    NUMBER("Number", DefaultLanguageHighlighterColors.NUMBER),
    STRING("String", DefaultLanguageHighlighterColors.STRING),
    KEYWORD("Keyword", DefaultLanguageHighlighterColors.KEYWORD),
    ;

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("com.vyperplugin.vyper.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
}