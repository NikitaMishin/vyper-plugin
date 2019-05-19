package com.vyperplugin.psi

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.project.Project

class VyperInternalTypeFactory(project : Project) : AbstractProjectComponent(project) {

    private val psiFactory: VyperPsiFactory = VyperPsiFactory(project)

    lateinit var  msg: VyperStructDefinition
        private set
    init{
        ApplicationManager.getApplication().runReadAction{
        msg = psiFactory.createStruct("""struct Msg:
        |   sender : address
        |   value : wei
        |   gas : uint256
    """.trimMargin())
        }
    }

    companion object {
        fun of(project: Project): VyperInternalTypeFactory {
            return project.getComponent(VyperInternalTypeFactory::class.java)
        }
    }


}