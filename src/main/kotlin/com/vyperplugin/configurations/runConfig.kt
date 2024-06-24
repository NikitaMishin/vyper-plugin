package org.vyperlang.plugin.configurations

//
//
//
//abstract class VyperRunConfigBase
//(configurationModule: VyperRunConfigModule, factory: ConfigurationFactory) :
//        ModuleBasedConfiguration<VyperRunConfigModule>(configurationModule, factory), RunConfigurationWithSuppressedDefaultDebugAction
//
//
//class VyperRunConfig() {
//
//}
//
//
//class VyperRunConfigurationType : ConfigurationTypeBase("VYPER RUN CONFIGURATION", "Vyper", "Vyper run configuration", VyperIcons.FILE) {
//
//    init {
//        addFactory(VyperConfigurationFactory(this))
//    }
//}
//
//class VyperConfigurationFactory(configurationType: VyperRunConfigurationType) : ConfigurationFactory(configurationType) {
//    override fun createTemplateConfiguration(project: Project): RunConfiguration {
//        val configurationModule = VyperRunConfigModule(p)
//    return VyperRunConfig(configurationModule,this)
//    }
//
//    override fun getIcon(): Icon {
//        return super.getIcon()
//    }
//
//    /**
//     * Override this method and return {@code false} to hide the configuration from 'New' popup in 'Edit Configurations' dialog. It will be
//     * still possible to create this configuration by clicking on '42 more items' in the 'New' popup.
//     *
//     * @return {@code true} if it makes sense to create configurations of this type in {@code project}
//     */
//    override fun isApplicable(project: Project): Boolean = true
//
//}
//
//class VyperRunConfigModule(p: Project) : RunConfigurationModule(p)