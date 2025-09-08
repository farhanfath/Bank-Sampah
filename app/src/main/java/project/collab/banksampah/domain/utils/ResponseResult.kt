package project.collab.banksampah.domain.utils

sealed class ResponseResult<out T> {
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error(val exception: Throwable) : ResponseResult<Nothing>()
}

inline fun <T> ResponseResult<T>.onSuccess(action: (data: T) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Success) action(data)
    return this
}

inline fun <T> ResponseResult<T>.onError(action: (exception: Throwable) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Error) action(exception)
    return this
}