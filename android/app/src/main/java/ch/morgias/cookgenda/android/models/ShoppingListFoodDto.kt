package ch.morgias.cookgenda.android.models

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingListFoodDto(
    val id: Long,
    val foodId: Long,
    val name: String,
    val quantity: Double,
    val checked: Boolean
)
