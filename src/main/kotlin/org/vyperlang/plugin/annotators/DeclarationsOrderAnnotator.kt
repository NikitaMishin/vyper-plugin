package org.vyperlang.plugin.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import org.vyperlang.plugin.psi.*
import kotlin.reflect.KClass

private const val GLOBAL_BEFORE_FUNCTION = "Global variables must all come before function definitions"
private const val IMPORT_BEFORE_GLOBAL = "Imports must come before global variables"
private val GlobalTypes = arrayOf(VyperStateVariableDeclaration::class, VyperConstantDefinitionExpression::class, VyperImmutableDefinitionExpression::class);

class DeclarationsOrderAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when {
            GlobalTypes.any { it.java.isInstance(element) } && findBefore(element, VyperFunctionDefinition::class) ->
                holder.newAnnotation(HighlightSeverity.ERROR, GLOBAL_BEFORE_FUNCTION).create()
            element is VyperImportDirective && findBefore(element, *GlobalTypes) ->
                holder.newAnnotation(HighlightSeverity.ERROR, IMPORT_BEFORE_GLOBAL).create()
        }
    }

    private fun findBefore(element: PsiElement, vararg types: KClass<out Any>) = element.file
        .elements
        .takeWhile { it != element }
        .any { types.any { type -> type.java.isInstance(it) } }
}
