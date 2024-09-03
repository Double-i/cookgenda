package ch.morgias.cookgenda.android.models

import ch.morgias.cookgenda.android.models.utils.LocalDateTimeSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ShoppingList(
    val id: Long,
    @Serializable(with = LocalDateTimeSerializer::class) @Contextual val fromDate: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class) @Contextual val toDate: LocalDateTime,
    val shoppingListFoods: Collection<ShoppingListFood>
)

@Serializable
data class ShoppingListWithFoodListByCategory(
    val id: Long,
    @Serializable(with = LocalDateTimeSerializer::class) @Contextual val fromDate: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class) @Contextual val toDate: LocalDateTime,
    val shoppingListFoods: Map<String, MutableList<ShoppingListFood>>
)