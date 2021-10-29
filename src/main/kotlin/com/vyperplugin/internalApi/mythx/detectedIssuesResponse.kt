package com.vyperplugin.internalApi.mythx

import com.google.gson.annotations.SerializedName


data class Description(
    @SerializedName("head")
    val head: String = "",
    @SerializedName("tail")
    val tail: String = ""
)


data class DetectedIssue(
    @SerializedName("sourceList")
    val sourceList: List<String>?,
    @SerializedName("sourceType")
    val sourceType: String = "",
    @SerializedName("meta")
    val meta: Any,
    @SerializedName("issues")
    val issues: List<IssuesItem>?,
    @SerializedName("sourceFormat")
    val sourceFormat: String = ""
)


data class IssuesItem(
    @SerializedName("severity")
    val severity: String = "",
    @SerializedName("swcTitle")
    val swcTitle: String = "",
    @SerializedName("swcID")
    val swcID: String = "",
    @SerializedName("extra")
    val extra: Any,
    @SerializedName("description")
    val description: Description,
    @SerializedName("locations")
    val locations: List<LocationsItem>?
)


data class LocationsItem(
    @SerializedName("sourceList")
    val sourceList: List<String>?,
    @SerializedName("sourceMap")
    val sourceMap: String = "",
    @SerializedName("sourceType")
    val sourceType: String = "",
    @SerializedName("sourceFormat")
    val sourceFormat: String = ""
)


