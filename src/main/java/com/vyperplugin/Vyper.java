package com.vyperplugin;

import com.intellij.lang.Language;

public class Vyper extends Language {
    public static final Vyper INSTANCE = new Vyper();

    private Vyper() {
        super("Vyper");
    }

}
