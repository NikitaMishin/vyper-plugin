package org.vyperlang.plugin.internalApi.mythx


data class AnalysisSubmitResponse(
    val harveyVersion: String,
    val submittedBy: String,
    val apiVersion: String,
    val mythrilVersion: String,
    val maruVersion: String,
    val submittedAt: String,
    val uuid: String,
    val maestroVersion: String,
    val queueTime: Int,
    val status: String
)