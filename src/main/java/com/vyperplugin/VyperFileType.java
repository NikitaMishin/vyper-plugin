package com.vyperplugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class VyperFileType extends LanguageFileType {

    public static final VyperFileType INSTANCE = new VyperFileType();

    private VyperFileType(){
        super(Vyper.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Vyper file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Vyper language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "vy";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return VyperIcons.FILE;
    }
}
