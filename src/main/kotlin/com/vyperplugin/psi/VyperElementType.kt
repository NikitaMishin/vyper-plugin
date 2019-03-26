package com.vyperplugin.psi

import com.intellij.psi.tree.IElementType
import com.vyperplugin.Vyper

class VyperElementType(debugName: String) : IElementType(debugName, Vyper.INSTANCE)
