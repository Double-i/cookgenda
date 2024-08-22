package ch.morgias.cookgenda.android.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

import java.time.LocalDateTime

@Serializable
data class PlaningDay(
    @Contextual val date: LocalDateTime,
    val recipe: List<Recipe>
)

fun createWeekPlaning(): List<PlaningDay> {
    val date = LocalDateTime.of(2024, 8, 19, 0, 0)
    return LongRange(0, 7).map {
        PlaningDay(date.plusDays(it), listOf(Recipe(1, "Tralala"), Recipe(1, "Tralala")))
    }
}