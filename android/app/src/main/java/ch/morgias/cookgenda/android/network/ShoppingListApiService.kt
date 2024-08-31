package ch.morgias.cookgenda.android.network

import ch.morgias.cookgenda.android.models.ShoppingListResume
import retrofit2.http.GET

interface ShoppingListApiService {

    @GET("planedRecipes")
    suspend fun getShoppingListResumes(): List<ShoppingListResume>

}

object ShoppingListApi {
    val retrofitService: ShoppingListApiService by lazy {
        retrofit.create(ShoppingListApiService::class.java)
    }
}