package ch.morgias.cookgenda.android.network

import ch.morgias.cookgenda.android.models.PlanedRecipe
import ch.morgias.cookgenda.android.models.PlaningDay
import ch.morgias.cookgenda.android.models.dto.NewPlanedRecipeDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDate

interface PlaningApiService {
    @GET("planedRecipes")
    suspend fun getPlaningForWeek(
        @Query("from") from: LocalDate,
        @Query("to") to: LocalDate
    ): List<PlaningDay>

    @POST("planedRecipes")
    suspend fun planRecipe(@Body newPlanedRecipe: NewPlanedRecipeDto): PlanedRecipe
}

object PlaningApi {
    val retrofitService: PlaningApiService by lazy {
        retrofit.create(PlaningApiService::class.java)
    }
}


