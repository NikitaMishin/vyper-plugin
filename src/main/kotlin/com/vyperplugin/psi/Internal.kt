package com.vyperplugin.psi

import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.project.Project

class VyperInternalTypeFactory(project : Project) : AbstractProjectComponent(project) {

    private val psiFactory: VyperPsiFactory = VyperPsiFactory(project)

    companion object {
        fun of(project: Project): VyperInternalTypeFactory {
            return project.getComponent(VyperInternalTypeFactory::class.java)
        }
    }

    val msg : VyperStructDefinition = psiFactory.createStruct("""struct Msg:
        |   sender : address
        |   value : wei
        |   gas : uint256
    """.trimMargin())
}