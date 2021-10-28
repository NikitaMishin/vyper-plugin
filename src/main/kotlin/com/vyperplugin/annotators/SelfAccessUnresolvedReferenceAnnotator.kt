package com.vyperplugin.annotators

//class SelfAccessUnresolvedReferenceAnnotator : Annotator {
//    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
//        if (element is VyperSelfAccessExpression) {
//            val reference = element.varLiteral.reference?.resolve()
//            if(reference == null) {
//                val annotatedElement = element.varLiteral
//                val range = annotatedElement.textRange
//                holder.createErrorAnnotation(range, "Unresolved contract member")
//            }
//        }
//    }
//}