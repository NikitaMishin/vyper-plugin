package org.vyperlang.plugin.settings

import com.intellij.openapi.fileChooser.FileChooserDescriptor

const val ChooseFiles = true
const val ChooseFolders = false
const val ChooseJars = false
const val ChooseJarsAsFiles = false
const val ChooseJarContents = false
const val ChooseMultiple = false

object VyperPathChooserDescriptor : FileChooserDescriptor(
        ChooseFiles, ChooseFolders, ChooseJars, ChooseJarsAsFiles, ChooseJarContents, ChooseMultiple)
