package ch.morgias.cookgenda.android.network

import ch.morgias.cookgenda.android.models.PlaningDay
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaningApiService {
    @GET("week/{week}/planing")
    suspend fun getPlaningForWeek(@Path("week") id: Int): List<PlaningDay>
}

object PlaningApi {
    val retrofitService: PlaningApiService by lazy {
        retrofit.create(PlaningApiService::class.java)
    }
}


