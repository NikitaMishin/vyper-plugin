package org.vyperlang.plugin.run

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
import org.vyperlang.plugin.gui.VyperRunContractCase
import org.vyperlang.plugin.gui.VyperRunMenuSingle
import org.vyperlang.plugin.psi.VyperFunctionDefinition
import java.awt.event.MouseEvent
import java.util.function.Supplier


class VyperMethodRunHandler(private val funcName: String, private val funcDef: org.vyperlang.plugin.psi.VyperFunctionDefinition) :
    GutterIconNavigationHandler<PsiElement> {
    /**
     * when user clicks on run icon
     * ui component for run method is open
     * and handler attaches to button run
     */
    override fun navigate(e: MouseEvent?, elt: PsiElement) {
        val file = funcDef.containingFile.virtualFile!!


        val frame = VyperRunMenuSingle(
            funcDef.project, ModuleManager.getInstance(funcDef.project).modules.first(), file
        )
        val funcArgs = getArgsFromSignature(funcDef)
        val initArgs = getInitArgsFromSignature(funcDef)

        frame.addPropertyChangeListener { event ->
            run {
                if (event.propertyName == VyperRunContractCase.SINGLE) {
                    ApplicationManager.getApplication().runWriteAction {
                        FileDocumentManager.getInstance().saveAllDocuments()
                        ProgressManager.getInstance()
                            .run(object : Task.Backgroundable(funcDef.project, "Running contract...") {
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
    private fun getArgsFromSignature(elt: org.vyperlang.plugin.psi.VyperFunctionDefinition): Array<String> {
        //    val type = elt.functionArgs!!.typeList.map { it.text }
        if (elt.functionArgs == null) {
            return arrayOf()
        }
        return elt.functionArgs!!.text.split(',').toTypedArray()
    }

    /**
     * return empty array if nothing
     */
    private fun getInitArgsFromSignature(elt: org.vyperlang.plugin.psi.VyperFunctionDefinition): Array<String> {

        val initMethod = elt.parent.children
            .filter { (it is org.vyperlang.plugin.psi.VyperFunctionDefinition) }
            .map { it as org.vyperlang.plugin.psi.VyperFunctionDefinition }
            .filter { it.identifier!!.text == "__init__" }
        if (initMethod.isNotEmpty() && initMethod.first().functionArgs != null) {
            return initMethod.first().functionArgs!!.text.split(',').toTypedArray()
        }

        return arrayOf()

    }
}

class VyperRunMethodLineMarkerProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? = null
    override fun collectSlowLineMarkers(
        elements: MutableList<out PsiElement>,
        result: MutableCollection<in LineMarkerInfo<*>>
    ) {
        for (element in elements) {
            when (element) {
                is org.vyperlang.plugin.psi.VyperFunctionDefinition -> {
                    element.identifier?.let { methodName ->
                        val func = { _: PsiElement -> "run ${methodName.text}" }
                        if (!(
                                    methodName.text == "__init__" || methodName.text == "__default__"
                                    )
                        ) result.add(
                            LineMarkerInfo(
                                methodName,
                                methodName.textRange,
                                AllIcons.RunConfigurations.TestState.Run,
                                func,
                                VyperMethodRunHandler(methodName.text, element),
                                GutterIconRenderer.Alignment.LEFT,
                                VyperSupplier(methodName.text)
                            )
                        )
                    } ?: continue
                }
            }

        }
    }

    private class VyperSupplier(val data: String) : Supplier<String> {

        override fun get(): String {
            return data
        }
    }
}
