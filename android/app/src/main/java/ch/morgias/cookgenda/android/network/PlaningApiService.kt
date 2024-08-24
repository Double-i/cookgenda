package ch.morgias.cookgenda.android.network

import ch.morgias.cookgenda.android.models.PlaningDay
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface PlaningApiService {
    @GET("planedRecipes")
    suspend fun getPlaningForWeek(
        @Query("from") from: LocalDate,
        @Query("to") to: LocalDate
    ): List<PlaningDay>
}

object PlaningApi {
    val retrofitService: PlaningApiService by lazy {
        retrofit.create(PlaningApiService::class.java)
    }
}


