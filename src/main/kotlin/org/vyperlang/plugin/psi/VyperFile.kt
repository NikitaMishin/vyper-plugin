package org.vyperlang.plugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import com.intellij.psi.TokenType.WHITE_SPACE
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.text.SemVer
import org.vyperlang.plugin.VyperLanguage
import org.vyperlang.plugin.psi.VyperTypes.COMMENT

private val CommentTokens = TokenSet.create(COMMENT, WHITE_SPACE)
private val PragmaRegex = "#\\s*(?:pragma\\s+|@)version\\s+\\^?(\\d+\\.\\d+\\.\\d+)".toRegex()


class VyperFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, VyperLanguage) {

    override fun getFileType() = viewProvider.fileType

    override fun toString() = fileType.name

    /**
     * Get the Vyper version from the pragma directive
     * This is kept internal, please use feature switches to enable/disable features
     * @return the Vyper version or null if not found
     */
    internal val vyperVersion: SemVer? get() =
        this.node.getChildren(null)
            .asSequence()
            .takeWhile { CommentTokens.contains(it.elementType) }
            .map {  PragmaRegex.findAll(it.text) }
            .filter { it.count() > 0 }
            .map { it.first().groupValues[1] }
            .firstOrNull()
            .let { SemVer.parseFromText(it) }

    val elements: List<VyperElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperElement::class.java)

    val selfElements: MutableList<VyperNamedElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperFunctionDefinition::class.java, VyperStateVariableDeclaration::class.java)

    val interfaces: List<VyperInterfaceDeclaration> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperInterfaceDeclaration::class.java)

    val imports: List<VyperImportDirective> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperImportDirective::class.java)

    val structs: List<VyperStructDeclaration> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperStructDeclaration::class.java)

    val events: List<VyperEventDeclaration> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperEventDeclaration::class.java)

    fun findStruct(name: String?): List<VyperStructDeclaration> = structs.filter { it.name == name }

}
