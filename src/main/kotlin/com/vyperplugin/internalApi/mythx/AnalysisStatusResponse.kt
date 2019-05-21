package com.vyperplugin.internalApi.mythx

import com.google.gson.annotations.SerializedName

data class AnalysisStatusResponse(
    @SerializedName("harveyVersion")
    val harveyVersion: String = "",
    @SerializedName("submittedBy")
    val submittedBy: String = "",
    @SerializedName("apiVersion")
    val apiVersion: String = "",
    @SerializedName("mythrilVersion")
    val mythrilVersion: String = "",
    @SerializedName("maruVersion")
    val maruVersion: String = "",
    @SerializedName("runTime")
    val runTime: Int = 0,
    @SerializedName("error")
    val error: String = "",
    @SerializedName("submittedAt")
    val submittedAt: String = "",
    @SerializedName("uuid")
    val uuid: String = "",
    @SerializedName("maestroVersion")
    val maestroVersion: String = "",
    @SerializedName("queueTime")
    val queueTime: Int = 0,
    @SerializedName("status")
    val status: String = ""
)