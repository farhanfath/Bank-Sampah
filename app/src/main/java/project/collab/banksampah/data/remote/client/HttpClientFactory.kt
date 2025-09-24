package project.collab.banksampah.data.remote.client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import project.collab.banksampah.BuildConfig
import project.collab.banksampah.data.local.TokenDataSource

object HttpClientFactory {

    fun create(tokenDataSource: TokenDataSource): HttpClient {
        return HttpClient(Android) {

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                    isLenient = true
                    prettyPrint = true
                })
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("HttpClient", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(createClientPlugin("tokenAuth") {
                onRequest { request, _ ->
                    runBlocking {
                        try {
                            val token = tokenDataSource.getAccessToken()
                            if (!token.isNullOrEmpty()) {
                                request.headers.remove("Authorization")
                                request.headers.append("Authorization", "Bearer $token")
                                Log.d("HttpClient", "🔐 Token applied: ${token.take(10)}...")
                            } else {
                                Log.d("HttpClient", "🔓 No token - proceeding without auth")
                            }
                        } catch (e: Exception) {
                            Log.e("HttpClient", "❌ Token check failed: ${e.message}")
                        }
                    }
                }
            })

            // Request timeout
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 30_000
                socketTimeoutMillis = 30_000
            }

            // Default request configuration
            defaultRequest {
                url(BuildConfig.BASE_URL)
                headers.append("Content-Type", "application/json")
                headers.append("Accept", "application/json")
            }
        }
    }
}