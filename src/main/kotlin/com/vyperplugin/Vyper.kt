package com.vyperplugin

import com.intellij.lang.Language

class Vyper : Language("Vyper") {

    companion object {
        val INSTANCE = Vyper()
    }

    override fun isCaseSensitive() = true

}
