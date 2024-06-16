package com.vyperplugin.psi

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.vyperplugin.annotators.VyperCompilerListener
import com.vyperplugin.annotators.VyperSmartCheckListener

class VyperInternalTypeFactory(project: Project) {

    private val psiFactory: VyperPsiFactory = VyperPsiFactory(project)

    lateinit var msg: VyperStructDefinition
        private set

    init {
        ApplicationManager.getApplication().runReadAction {
            msg = psiFactory.createStruct(
                """struct Msg:
        |   sender : address
        |   value : uint256
        |   gas : uint256
    """.trimMargin()
            )
        }

        VyperCompilerListener(project).listenAnalysis()
        VyperSmartCheckListener(project).listenAnalysis()
    }

    companion object {
        fun of(project: Project): VyperInternalTypeFactory {
            return project.getComponent(VyperInternalTypeFactory::class.java)
        }
    }


}