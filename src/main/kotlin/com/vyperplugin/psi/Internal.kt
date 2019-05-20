package com.vyperplugin.psi

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.project.Project
import com.vyperplugin.annotators.VyperSmartCheckListener

@SuppressWarnings("Deprecated")
class VyperInternalTypeFactory(project : Project) : AbstractProjectComponent(project) {

    private val psiFactory: VyperPsiFactory = VyperPsiFactory(project)

    lateinit var  msg: VyperStructDefinition
        private set
    init{
        ApplicationManager.getApplication().runReadAction{
        msg = psiFactory.createStruct("""struct Msg:
        |   sender : address
        |   value : wei_value
        |   gas : uint256
    """.trimMargin())
        }
        VyperSmartCheckListener(project).listenAnalysis()
    }

    companion object {
        fun of(project: Project): VyperInternalTypeFactory {
            return project.getComponent(VyperInternalTypeFactory::class.java)
        }
    }


}