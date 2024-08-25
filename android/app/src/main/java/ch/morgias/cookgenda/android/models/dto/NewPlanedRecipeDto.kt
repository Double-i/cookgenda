package ch.morgias.cookgenda.android.models.dto

import ch.morgias.cookgenda.android.models.utils.LocalDateTimeSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class NewPlanedRecipeDto(
    val recipeId: Int,
    @Serializable(with = LocalDateTimeSerializer::class) @Contextual val date: LocalDateTime
)