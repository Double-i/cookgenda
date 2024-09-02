package ch.morgias.cookgenda.android.models

import ch.morgias.cookgenda.android.models.utils.LocalDateSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class ShoppingListItem(
    val shoppingListId: Long,
    val name: String,
    val quantity: Double,
    @Serializable(with = LocalDateSerializer::class) @Contextual val plannedDate: LocalDate,
    val checked: Boolean
)
