package org.vyperlang.plugin.settings

import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.*
import com.intellij.util.text.SemVer
import javax.swing.JPanel

private const val DOWNLOAD_VYPER =
    "Please browse to the Vyper executable file. It may be downloaded from <a href='https://github.com/vyperlang/vyper/releases'>GitHub</a>."
private const val USE_BINARY = "Use binary for Vyper execution"
private const val USE_DOCKER_HELP = "When unselected, we will automatically try to run the correct Vyper version for you using Docker."
private fun selectVersion(version: SemVer) = "Select Vyper $version path"
private fun vyperVersionPath(version: SemVer) = "Vyper $version path:"
private val presetVersions = arrayOf(
    SemVer.parseFromText("0.3.10")!!,
    SemVer.parseFromText("0.4.0")!!
)


/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val localPaths = presetVersions.associateWith { "" }.toMutableMap()

    private lateinit var binaryVyperCheckbox: Cell<JBCheckBox>

    val panel: JPanel = panel {
        row {
            binaryVyperCheckbox = checkBox(USE_BINARY)
                .comment(USE_DOCKER_HELP)
                .bindSelected(
                    { executor == VyperExecutor.Local },
                    { selected -> executor = if (selected) VyperExecutor.Local else VyperExecutor.Local }
                )
        }
        presetVersions.map {
            row {
                label(vyperVersionPath(it))
                textFieldWithBrowseButton(selectVersion(it), null, VyperPathChooserDescriptor)
                    .bindText({ localPaths[it]!! }, { text -> localPaths[it] = text })
                    .comment(DOWNLOAD_VYPER)
                    .enabledIf(binaryVyperCheckbox.selected)
            }
        }
        buttonsGroup {
            row {
                button("Check Configuration") {

                }
            }
        }
    }

    var executor: VyperExecutor = VyperExecutor.Docker
}
