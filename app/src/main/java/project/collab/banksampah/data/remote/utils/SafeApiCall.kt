package project.collab.banksampah.data.remote.utils

import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.io.IOException
import project.collab.banksampah.domain.utils.ResponseResult
import java.net.ConnectException
import java.net.UnknownHostException

suspend inline fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResponseResult<T> {
    return try {
        ResponseResult.Success(apiCall())
    } catch (exception: ResponseException) {
        ResponseResult.Error(
            when (exception.response.status.value) {
                400 -> Exception("Bad request - please check your input")
                401 -> Exception("Unauthorized - please login again")
                403 -> Exception("Forbidden - you don't have permission")
                404 -> Exception("Resource not found")
                408 -> Exception("Request timeout - please try again")
                409 -> Exception("Conflict - data already exists")
                422 -> Exception("Validation error - please check your data")
                429 -> Exception("Too many requests - please wait and try again")
                500 -> Exception("Server error - please try again later")
                502 -> Exception("Bad gateway - server temporarily unavailable")
                503 -> Exception("Service unavailable - please try again later")
                else -> Exception("Network error: ${exception.message}")
            }
        )
    } catch (e: TimeoutCancellationException) {
        ResponseResult.Error(Exception("Request timed out - please check your connection"))
    } catch (e: ConnectException) {
        ResponseResult.Error(Exception("Unable to connect to server"))
    } catch (e: UnknownHostException) {
        ResponseResult.Error(Exception("No internet connection"))
    } catch (e: IOException) {
        ResponseResult.Error(Exception("Network error - please check your connection"))
    } catch (exception: Exception) {
        ResponseResult.Error(Exception("An unexpected error occurred: ${exception.message}"))
    }
}