package project.collab.banksampah.domain.utils

sealed class ResponseResult<out T> {
    object Loading : ResponseResult<Nothing>()
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error(val exception: Throwable) : ResponseResult<Nothing>()
}