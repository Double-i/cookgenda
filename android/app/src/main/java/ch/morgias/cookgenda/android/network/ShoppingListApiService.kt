package ch.morgias.cookgenda.android.network

import ch.morgias.cookgenda.android.models.ShoppingList
import ch.morgias.cookgenda.android.models.ShoppingListResume
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.time.LocalDate

interface ShoppingListApiService {

    @GET("shoppingLists")
    suspend fun getShoppingListResumes(): List<ShoppingListResume>

    @GET("shoppingLists/{shoppingListId}")
    suspend fun getShoppingList(@Path("shoppingListId") id: Int): ShoppingList

    @POST("shoppingLists")
    suspend fun generateShoppingList(
        @Path("shoppingListId") startDate: LocalDate,
        endDate: LocalDate
    ): ShoppingListResume

}

object ShoppingListApi {
    val retrofitService: ShoppingListApiService by lazy {
        retrofit.create(ShoppingListApiService::class.java)
    }
}