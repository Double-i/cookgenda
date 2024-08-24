package ch.morgias.cookgenda.android.models

import ch.morgias.cookgenda.android.models.utils.LocalDateTimeSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

import java.time.LocalDateTime

@Serializable
data class PlaningDay(

    @Serializable(with = LocalDateTimeSerializer::class)
    @Contextual val date: LocalDateTime,
    val recipes: List<Recipe>
)

fun createWeekPlaning(page: Int): List<PlaningDay> {
    val date = LocalDateTime.of(2024, 8, 19, 0, 0)
    return LongRange(0, 7).map {
        PlaningDay(date.plusDays(it + page * 7), listOf(Recipe(1, "Tralala"), Recipe(1, "Tralala")))
    }
}