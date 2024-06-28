package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import org.vyperlang.plugin.psi.VyperFile
import org.vyperlang.plugin.psi.VyperFunctionDefinition
import org.vyperlang.plugin.psi.VyperStateVariableDeclaration
import org.vyperlang.plugin.psi.file

//Events must be declared before global declarations and function definitions.

//Global variables must all come before function definitions

class VyperDeclarationsOrderAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {

        if (element is VyperStateVariableDeclaration) {
            val declarations = (element.file as VyperFile).getStatements().takeWhile { it != element }
            loop@ for (dec in declarations) {
                when (dec) {

                    is VyperFunctionDefinition -> {
                        holder.newAnnotation(
                            HighlightSeverity.ERROR, "Global variables must all come " +
                                    "before function definitions"
                        )
                        break@loop
                    }
                }
            }
        }
    }
}