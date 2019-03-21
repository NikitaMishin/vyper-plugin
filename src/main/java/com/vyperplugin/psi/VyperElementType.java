package com.vyperplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.vyperplugin.Vyper;

public class VyperElementType extends IElementType {

    public VyperElementType(String debugName) {
        super(debugName, Vyper.INSTANCE);
    }
}
