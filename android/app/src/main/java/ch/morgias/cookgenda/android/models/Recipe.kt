package ch.morgias.cookgenda.android.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(val id: Int, val name: String) {
}