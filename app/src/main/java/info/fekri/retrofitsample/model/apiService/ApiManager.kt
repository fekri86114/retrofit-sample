package info.fekri.retrofitsample.model.apiService

import info.fekri.retrofitsample.model.data.FoxData
import info.fekri.retrofitsample.util.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    private val apiService: ApiService

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

    }

    fun getRandomFox(apiCallback: ApiCallback<FoxData>) {
        apiService.getRandomFox().enqueue(object : Callback<FoxData>{
            override fun onResponse(call: Call<FoxData>, response: Response<FoxData>) {
                apiCallback.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<FoxData>, t: Throwable) {
                apiCallback.onFailure(t.message ?: "null-message")
            }
        })
    }

    interface ApiCallback<T> {
        fun onSuccess(data: T)
        fun onFailure(msg: String)
    }

}