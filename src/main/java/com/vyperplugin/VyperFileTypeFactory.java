package com.vyperplugin;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;


public class VyperFileTypeFactory extends FileTypeFactory {

    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(VyperFileType.INSTANCE);
    }
}
