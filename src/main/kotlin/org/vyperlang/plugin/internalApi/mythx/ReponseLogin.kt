package org.vyperlang.plugin.internalApi.mythx


import com.google.gson.annotations.SerializedName

//"""
//"jwtTokens":
//{
//
//    "access": "string",
//    "refresh": "string"
//
//},
//"access": "string",
//"refresh": "string"
//"""


data class LoginReponse(
    @SerializedName("access")
    val access: String = "",
    @SerializedName("refresh")
    val refresh: String = "",
    @SerializedName("jwtTokens")
    val jwtTokens: JwtTokens
)


data class RefreshResponse(
    @SerializedName("jwtTokens")
    val jwtTokens: JwtTokens
)

data class JwtTokens(
    @SerializedName("access")
    val access: String = "",
    @SerializedName("refresh")
    val refresh: String = ""
)


