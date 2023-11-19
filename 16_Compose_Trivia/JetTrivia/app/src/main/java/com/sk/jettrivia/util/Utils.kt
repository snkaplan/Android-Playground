package com.sk.jettrivia.util

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error(message = response.message())
        }
    } catch (e: HttpException) {
        ApiResult.Error(message = e.message())
    } catch (e: Throwable) {
        ApiResult.Error(message = e.message ?: "General Exception")
    }
}

sealed class ApiResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    // We'll wrap our data in this 'Success'
    // class in case of success response from api
    class Success<T>(data: T?) : ApiResult<T>(data)

    // We'll pass error message wrapped in this 'Error'
    // class to the UI in case of failure response
    class Error<T>(data: T? = null, message: String) : ApiResult<T>(data, message)

    // We'll just pass object of this Loading
    // class, just before making an api call
    class Loading<T> : ApiResult<T>()
}
