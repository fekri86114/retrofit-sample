package info.fekri.retrofitsample.model.apiService

import info.fekri.retrofitsample.model.data.FoxData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // https://randomfox.ca/floof/?ref=apilist.fun

    @GET("/floof/")
    fun getRandomFox(
        @Query("ref")
        ref: String = "apilist.fun"
    ): Call<FoxData>

}