package org.vyperlang.plugin

import com.intellij.lang.Language

object VyperLanguage : Language("Vyper", "text/vyper") {

    override fun isCaseSensitive() = true
}


