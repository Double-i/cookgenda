package ch.morgias.cookgenda.android.models.dto

import ch.morgias.cookgenda.android.models.utils.LocalDateSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class DatePeriodDto(
    @Serializable(with = LocalDateSerializer::class) @Contextual val fromDate: LocalDate,
    @Serializable(with = LocalDateSerializer::class) @Contextual val toDate: LocalDate
)