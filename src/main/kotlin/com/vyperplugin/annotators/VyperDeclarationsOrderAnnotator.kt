package com.vyperplugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import com.vyperplugin.psi.*

//Events must be declared before global declarations and function definitions.

//Global variables must all come before function definitions

class VyperDeclarationsOrderAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {

        if (element is VyperStateVariableDeclaration) {

            val declarations = (element.file as VyperFile).getStatements().takeWhile { it != element }
            loop@ for( dec in declarations) {
                when(dec){

                    is VyperFunctionDefinition ->{ holder.createErrorAnnotation(element, "Global variables must all come " +
                            "before function definitions")
                        break@loop
                }
                }
            }
        }
    }
}