package ch.morgias.cookgenda.android.network

import ch.morgias.cookgenda.android.models.ShoppingList
import ch.morgias.cookgenda.android.models.ShoppingListResume
import ch.morgias.cookgenda.android.models.dto.CheckShoppingFoodDto
import ch.morgias.cookgenda.android.models.dto.DatePeriodDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
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


    @PATCH("shoppingListFoods/{shoppingFoodId}")
    suspend fun checkShoppingFoodId(
        @Path("shoppingFoodId") shoppingFoodId: Long,
        @Body check: CheckShoppingFoodDto
    )

}

object ShoppingListApi {
    val retrofitService: ShoppingListApiService by lazy {
        retrofit.create(ShoppingListApiService::class.java)
    }
}