package ch.morgias.cookgenda.android.network

import ch.morgias.cookgenda.android.models.Recipe
import ch.morgias.cookgenda.android.models.RecipeDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {
    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("recipes/{id}")
    suspend fun getRecipe(@Path("id") id: Int): RecipeDetails
}

object RecipeApi {
    val retrofitService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}


