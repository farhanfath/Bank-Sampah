package project.collab.banksampah.data.remote.client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
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

            install(Auth) {
                bearer {
                    loadTokens {
                        runBlocking {
                            val token = tokenDataSource.getToken().first()
                            if (token.isNotEmpty()) {
                                BearerTokens(accessToken = token, refreshToken = "")
                            } else {
                                null
                            }
                        }
                    }
                }
            }

            // Request timeout
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 30_000
                socketTimeoutMillis = 30_000
            }

            // Default request configuration
            defaultRequest {
                url("https://your-api-base-url.com")
                headers.append("Content-Type", "application/json")
                headers.append("Accept", "application/json")
            }
        }
    }
}