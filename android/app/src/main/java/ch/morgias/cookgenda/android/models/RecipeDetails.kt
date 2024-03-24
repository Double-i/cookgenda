package ch.morgias.cookgenda.android.models

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetails(
    val id: Int,
    val name: String,
    val description: String?,
    val image: String?
) {

}
