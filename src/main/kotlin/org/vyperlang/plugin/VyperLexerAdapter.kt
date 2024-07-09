package org.vyperlang.plugin

import com.intellij.lexer.FlexAdapter
import org.vyperlang.plugin.grammar._BaseVyperLexer
import java.io.Reader

class VyperLexerAdapter : FlexAdapter(_BaseVyperLexer(null as Reader?))
