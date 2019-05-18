package com.vyperplugin.run

import com.intellij.codeInsight.daemon.GutterIconNavigationHandler
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.icons.AllIcons
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.psi.PsiElement
import com.intellij.util.Function
import com.vyperplugin.gui.VyperRunContractCase
import com.vyperplugin.gui.VyperRunMenuSingle
import com.vyperplugin.psi.VyperFunctionDefinition
import java.awt.event.MouseEvent


class VyperMethodRunHandler(private val funcName: String) : GutterIconNavigationHandler<PsiElement> {
    /**
     * when user clicks on run icon
     * ui component for run method is open
     * and handler attaches to button run
     */
    override fun navigate(e: MouseEvent?, elt: PsiElement) {
        val file = elt.containingFile.virtualFile!!



        val frame = VyperRunMenuSingle(
                elt.project, ModuleManager.getInstance(elt.project).modules.first(), file)
        val funcArgs = getArgsFromSignature(elt as VyperFunctionDefinition)
        val initArgs = getInitArgsFromSignature(elt)

        frame.addPropertyChangeListener { event ->
            run {
                if (event.propertyName == VyperRunContractCase.SINGLE) {
                    ApplicationManager.getApplication().runWriteAction {
                        FileDocumentManager.getInstance().saveAllDocuments()
                        ProgressManager.getInstance().run(object : Task.Backgroundable(elt.project, "running contract...") {
                            override fun run(indicator: ProgressIndicator) {
                                            VyperRun.testContractSingleMethod(event.newValue as VyperTestParameters)
                            }
                        })
                    }

                } else {
                    //TODO useless branch for now
                    VyperRun.testContractMultipleMethod(event.newValue as VyperTestParameters)
                }
            }
        }

        frame.displaySingle(file.name, funcName, funcArgs, initArgs)

    }

    /**
     * return empty array if nothing
     */
    private fun getArgsFromSignature(elt: VyperFunctionDefinition): Array<String> {
        //    val type = elt.functionArgs!!.typeList.map { it.text }
        if (elt.functionArgs == null) {
            return arrayOf()
        }
        return elt.functionArgs!!.text.split(',').toTypedArray()
    }

    /**
     * return empty array if nothing
     */
    private fun getInitArgsFromSignature(elt: VyperFunctionDefinition): Array<String> {

        val initMethod = elt.parent.children
                .filter { (it is VyperFunctionDefinition) }
                .map { it as VyperFunctionDefinition }
                .filter { it.identifier!!.text == "__init__" }
        if (initMethod.isNotEmpty() && initMethod.first().functionArgs != null) {
            return initMethod.first().functionArgs!!.text.split(',').toTypedArray()
        }

        return arrayOf()

    }
}


class VyperRunMethodLineMarkerProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? = null

    override fun collectSlowLineMarkers(elements: MutableList<PsiElement>, result: MutableCollection<LineMarkerInfo<PsiElement>>) {
        loop@ for (element in elements) {
            when (element) {
                is VyperFunctionDefinition -> {
                    if (element.identifier == null) continue@loop

                    val methodName = element.identifier

                    if (methodName == null ||
                            methodName.text == "__init__" || methodName.text == "__default__") continue@loop

                    result.add(
                            LineMarkerInfo(element,
                                    methodName.textRange,
                                    AllIcons.RunConfigurations.TestState.Run, 0,
                                    Function { param: PsiElement -> "run ${(param as VyperFunctionDefinition).identifier!!.text}" },
                                    VyperMethodRunHandler(methodName.text), GutterIconRenderer.Alignment.LEFT)
                    )
                }
            }

        }
    }
}
