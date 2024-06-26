package org.vyperlang.plugin.psi

import com.intellij.psi.tree.IElementType
import org.vyperlang.plugin.VyperLanguage

class VyperElementType(debugName: String) : IElementType(debugName, VyperLanguage)
