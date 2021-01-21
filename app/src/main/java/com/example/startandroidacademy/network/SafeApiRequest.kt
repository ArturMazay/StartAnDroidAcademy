package com.example.startandroidacademy.network

import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!   ///вот если тут удачното парсим?
        } else {
            val error = response.errorBody()?.toString()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))

                } catch (e: Exception) {
                    message.append("\n")

                }
            }
            message.append("Error code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}
class ApiException(message: String): IOException(message)