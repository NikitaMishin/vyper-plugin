package org.vyperlang.plugin.internalApi.mythx

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type

class APIMythX {
    private val cite = "https://api.mythx.io/v1/"
    private val loginEndPoint = "${cite}auth/login"
    private val refreshTokenEndPoint = "${cite}auth/refresh"
    private val analyzeEndPoint = "${cite}analyses"
    private val gson = Gson()

    val waitTimeOfAnalysis = 15000L // in seconds
    val retryCounts = 5//  minutes at most


    private fun <ReturnType> whenHandler(
        response: Response,
        result: Result<String, FuelError>,
        typeToken: Type
    ): Pair<ReturnType?, ErrorResponse?> {
        return when (response.statusCode) {
            200 -> {
                Pair(gson.fromJson(result.component1()!!, typeToken), null)
            }
            401 -> {
                return try {
                    Pair(
                        null, gson.fromJson(response.body().asString("application/json"), ErrorResponse::class.java)
                    )
                } catch (err: Error) {
                    Pair(null, ErrorResponse(response.body().asString("application/json"), 401))
                }
            }
            else -> {
                Pair(null, gson.fromJson(response.body().asString("application/json"), ErrorResponse::class.java))
            }
        }
    }

    suspend fun login(ethAddress: String, password: String): Pair<LoginReponse?, ErrorResponse?> {
        val (_, response, result) =
            Fuel.post(loginEndPoint)
                .jsonBody("{ \"ethAddress\" : \"$ethAddress\", \"password\" : \"$password\" }")
                .awaitStringResponseResult()
        return whenHandler(response, result, object : TypeToken<LoginReponse>() {}.type)
    }


    suspend fun refreshAuthToken(tokens: JwtTokens): Pair<JwtTokens?, ErrorResponse?> {
        val (_, response, result) =
            Fuel.post(refreshTokenEndPoint)
                .jsonBody(
                    "{  \"jwtTokens\" : { \"access\" : \"${tokens.access}\", \"refresh\" : \"${tokens.refresh}\" } }"
                )
                .awaitStringResponseResult()
        return whenHandler(response, result, object : TypeToken<JwtTokens>() {}.type)
    }

    suspend fun getAnalysisStatus(uuid: String, tokens: JwtTokens): Pair<AnalysisStatusResponse?, ErrorResponse?> {
        val (_, response, result) =
            Fuel.get("$analyzeEndPoint/$uuid")
                .header(Headers.AUTHORIZATION, "Bearer ${tokens.access}")
                .awaitStringResponseResult()

        return whenHandler(response, result, object : TypeToken<AnalysisStatusResponse>() {}.type)
    }

    suspend fun getDetectedIssue(uuid: String, tokens: JwtTokens): Pair<List<DetectedIssue>?, ErrorResponse?> {
        val (_, response, result) =
            Fuel.get("$analyzeEndPoint/$uuid/issues")
                .header(Headers.AUTHORIZATION, "Bearer ${tokens.access}")
                .awaitStringResponseResult()

        return whenHandler(response, result, object : TypeToken<List<DetectedIssue>>() {}.type)
    }

    suspend fun partialAnalyze(tokens: JwtTokens, bytecode: String, deployedBytecode: String):
            Pair<AnalysisSubmitResponse?, ErrorResponse?> {
        val (_, response, result) =
            Fuel.post(analyzeEndPoint)
                .header(Headers.AUTHORIZATION, "Bearer ${tokens.access}")
                .jsonBody(
                    """
                        {
                          "clientToolName":"vyper-intelij-plugin",
                          "data": {
                               "bytecode": "$bytecode",
                               "deployedBytecode" : "$deployedBytecode"
                          }
                        }

                    """.trimIndent()
                )
                .awaitStringResponseResult()
        return whenHandler(response, result, object : TypeToken<AnalysisSubmitResponse>() {}.type)
    }
}