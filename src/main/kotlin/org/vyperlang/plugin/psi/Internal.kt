package org.vyperlang.plugin.psi

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project

class VyperInternalTypeFactory(project: Project) {

    private val psiFactory: VyperPsiFactory = VyperPsiFactory(project)

    lateinit var msg: VyperStructDeclaration
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
    }
}