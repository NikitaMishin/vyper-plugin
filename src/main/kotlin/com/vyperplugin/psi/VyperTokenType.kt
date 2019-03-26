package com.vyperplugin.psi

import com.intellij.psi.tree.IElementType
import com.vyperplugin.Vyper

class VyperTokenType(debugName: String) : IElementType(debugName, Vyper.INSTANCE) {

    override fun toString(): String {
        return "VyperTokenType." + super.toString()
    }
}
