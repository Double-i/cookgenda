package ch.morgias.cookgenda.android.network

import ch.morgias.cookgenda.android.models.ShoppingList
import ch.morgias.cookgenda.android.models.ShoppingListResume
import ch.morgias.cookgenda.android.models.dto.DatePeriodDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ShoppingListApiService {

    @GET("shoppingLists")
    suspend fun getShoppingListResumes(): List<ShoppingListResume>

    @GET("shoppingLists/{shoppingListId}")
    suspend fun getShoppingList(@Path("shoppingListId") id: Int): ShoppingList

    @POST("shoppingLists")
    suspend fun generateShoppingList(
        @Body data: DatePeriodDto
    ): ShoppingListResume

}

object ShoppingListApi {
    val retrofitService: ShoppingListApiService by lazy {
        retrofit.create(ShoppingListApiService::class.java)
    }
}