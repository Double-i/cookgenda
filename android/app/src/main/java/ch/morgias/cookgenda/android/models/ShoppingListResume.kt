package ch.morgias.cookgenda.android.models

import ch.morgias.cookgenda.android.models.utils.LocalDateSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate


@Serializable
data class ShoppingListResume(
    val id: Long,
    @Serializable(with = LocalDateSerializer::class) @Contextual val fromDate: LocalDate,
    @Serializable(with = LocalDateSerializer::class) @Contextual val toDate: LocalDate,
    val shoppingListSize: Int,
    val numberOfCheckedFoods: Int
)