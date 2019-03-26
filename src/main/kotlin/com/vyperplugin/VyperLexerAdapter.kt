package com.vyperplugin

import com.intellij.lexer.FlexAdapter
import java.io.Reader

class VyperLexerAdapter : FlexAdapter(_VyperLexer(null as Reader?))
