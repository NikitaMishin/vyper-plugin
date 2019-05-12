package com.vyperplugin.run

import com.intellij.openapi.vfs.VirtualFile


/**
 * represent input for testing
 * @callSequence provides either single method name "method"
 * or sequence of calls with arguments "method1(arg1,arg2);method2();...methodn(argn)"
 * @initArgs either empty if __init__() without args or arrayOf(arg1,arg2,argn)
 * @callArgs either  empty if called method has no args or arrayOf(arg1,arg2,argn)
 */
data class VyperTestParameters(
        val file: VirtualFile,
        val callSequence: String,
        val initArgs: Array<String>,
        val callArgs: Array<String>
)

object VyperRun {

    /**
     * test single method of contract with vyper-run inside docker container
     */
    fun testContractSingleMethod(testParams: VyperTestParameters) {
        TODO()
    }

    /**
     * test multiple methods of contract with vyper-run inside docker container
     */
    fun testContractMultipleMethod(testParams: VyperTestParameters) {
        TODO()
    }
}
