package br.senai.sp.jandira.limpeanapp.dados.api

import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("cadastro")
    fun cadastrarUsuario(@Body requestBody : RequestBody) : Call<ResponseBody>
}

class FakeApiService : ApiService { // Assuming YourApiService is an interface
    override fun cadastrarUsuario(requestBody: RequestBody): Call<ResponseBody> {
        return object : Call<ResponseBody> {
            override fun enqueue(callback: Callback<ResponseBody>?) {
                // In a real implementation, you would handle the callback here.
                // For the fake implementation, you can just call onResponse with a dummy response.
                val responseBody = ResponseBody.create(null, "Fake response body")
                callback?.onResponse(this, Response.success(responseBody))
            }

            // Implement other methods of the Call interface as needed.
            // For a fake implementation, you can provide empty or default implementations.
            override fun clone(): Call<ResponseBody> {
                return this
            }

            override fun execute(): Response<ResponseBody> {
                val responseBody = ResponseBody.create(null, "Fake response body")
                return Response.success(responseBody)
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }

            override fun cancel() {}
        }
    }
}