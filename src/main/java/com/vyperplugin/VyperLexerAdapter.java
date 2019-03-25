package com.vyperplugin;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

public class VyperLexerAdapter extends FlexAdapter {
    public VyperLexerAdapter(){
        super(new _VyperLexer((Reader)null));
    }
}
