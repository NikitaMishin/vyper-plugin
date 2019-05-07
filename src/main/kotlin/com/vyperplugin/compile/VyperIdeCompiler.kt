//package com.vyperplugin.compile
//
//import com.intellij.openapi.application.runReadAction
//import com.intellij.openapi.compiler.*
//import com.intellij.openapi.diagnostic.logger
//import com.intellij.psi.stubs.StubIndex
//import com.vyperplugin.VyperFileType
//import java.io.DataInput
//
//private val log = logger<VyperIdeCompiler>()
//
////
//// use timestamp as indicator that file changed
//// see TimestampValidityState
////
//fun DataInput?.toValidityState(): ValidityState {
//    if (this != null) try {
//        return TimestampValidityState.load(this)
//    } catch (e: Exception) {
//        log.warn("Failed to read a timestamp", e)
//    }
//    return EmptyValidityState()
//}
//
//
//object VyperIdeCompiler : Validator {
//
//    // try get from caches
//    override fun createValidityState(data: DataInput?): ValidityState {
//        return data.toValidityState()
//    }
//
//    override fun getProcessingItems(context: CompileContext): Array<FileProcessingCompiler.ProcessingItem> {
//        if(false){
//            return FileProcessingCompiler.ProcessingItem.EMPTY_ARRAY
//        }
//
//        val scope = context.compileScope
//        val file = scope.getFile(VyperFileType.INSTANCE,true)
//                .asSequence().filterNotNull()
//
//        return runReadAction {
//            val importKeys = StubIndex.getElements()
//
//        }
//
//    }
//
//
//    override fun process(context: CompileContext?, items: Array<out FileProcessingCompiler.ProcessingItem>?): Array<FileProcessingCompiler.ProcessingItem> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun validateConfiguration(scope: CompileScope?): Boolean {
//        return true
//        // file exists and not null
//     }
//
//    override fun getDescription(): String = "Vyper Language Compiler"
//}
//
//
//

