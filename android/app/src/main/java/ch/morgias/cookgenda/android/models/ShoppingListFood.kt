package ch.morgias.cookgenda.android.models

import ch.morgias.cookgenda.android.models.utils.LocalDateSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class ShoppingListFood(
    val id: Long,
    val foodId: Long,
    val shoppingListId: Long,
    val name: String,
    val quantity: Double,
    @Serializable(with = LocalDateSerializer::class) @Contextual val planedDate: LocalDate,
    val checked: Boolean,
)