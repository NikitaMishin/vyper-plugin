package com.vyperplugin.psi

import com.intellij.psi.tree.IElementType
import com.vyperplugin.VyperLanguage

class VyperTokenType(debugName: String) : IElementType(debugName, VyperLanguage) {

    override fun toString(): String {
        return "VyperTokenType." + super.toString()
    }
}
