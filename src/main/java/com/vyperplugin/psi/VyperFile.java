package com.vyperplugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.vyperplugin.Vyper;
import com.vyperplugin.VyperFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class VyperFile extends PsiFileBase {

    public VyperFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider,  Vyper.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return VyperFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Vyper File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
