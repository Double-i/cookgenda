package ch.morgias.cookgenda.android.models

import ch.morgias.cookgenda.android.models.utils.LocalDateTimeSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

import java.time.LocalDateTime

@Serializable
data class PlaningDay(

    @Serializable(with = LocalDateTimeSerializer::class)
    @Contextual val date: LocalDateTime,
    val recipes: List<PlanedRecipe>
)
