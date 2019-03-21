package com.vyperplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.vyperplugin.Vyper;

public class VyperTokenType extends IElementType {

    public VyperTokenType(String debugName) {
        super(debugName, Vyper.INSTANCE);
    }

    @Override
    public String toString() {
        return "VyperTokenType." + super.toString();
    }
}
