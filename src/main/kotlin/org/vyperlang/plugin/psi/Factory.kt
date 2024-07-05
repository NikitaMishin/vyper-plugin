package org.vyperlang.plugin.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import org.vyperlang.plugin.VyperFileType

class VyperPsiFactory(val project: Project) {
    fun createStruct(struct: String): VyperStructDefinition =
        createFromText(struct) ?: error("Failed to create struct: `$struct`")

    private inline fun <reified T : VyperElement> createFromText(code: String): T? =
        PsiFileFactory.getInstance(project)
            .createFileFromText("DUMMY.vy", VyperFileType.INSTANCE, code)
            .childOfType()
}
