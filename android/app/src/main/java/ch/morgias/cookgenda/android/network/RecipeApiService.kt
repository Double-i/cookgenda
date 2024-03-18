package ch.morgias.cookgenda.android.network

import retrofit2.http.GET

interface RecipeApiService {
    @GET("recipes")
    fun getRecipes(): String
}

object RecipeApi {
    val retrofitService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}


