package org.vyperlang.plugin.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.text.SemVer
import org.vyperlang.plugin.VyperLanguage
import org.vyperlang.plugin.psi.VyperTypes.COMMENT

private val CommentTokens = TokenSet.create(COMMENT)
private val PragmaRegex = "#\\s*pragma\\s+version\\s+(\\d+\\.\\d+\\.\\d+)".toRegex()
private val Vyper4 = SemVer.parseFromText("0.4.0")

class VyperFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, VyperLanguage) {

    override fun getFileType() = viewProvider.fileType

    override fun toString() = fileType.name

    /**
     * Get the Vyper version from the pragma directive
     * This is kept private, please use feature switches to enable/disable features
     * @return the Vyper version or null if not found
     */
    private val vyperVersion: SemVer? get() =
        this.node.getChildren(null)
            .asSequence()
            .takeWhile { CommentTokens.contains(it.elementType) }
            .map {  PragmaRegex.findAll(it.text) }
            .filter { it.count() > 0 }
            .map { it.first().groupValues[1] }
            .firstOrNull()
            .let { SemVer.parseFromText(it) }

    val deprecateEntrancyKey: Boolean? get() = vyperVersion?.let { it >= Vyper4 }

    val elements: List<VyperElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperElement::class.java)

    val selfElements: MutableList<VyperNamedElement> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperFunctionDefinition::class.java, VyperStateVariableDeclaration::class.java)

    val interfaces: List<VyperInterfaceDeclaration> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperInterfaceDeclaration::class.java)

    val imports: List<VyperImportDirective> get() = PsiTreeUtil.getChildrenOfAnyType(this, VyperImportDirective::class.java)

}
