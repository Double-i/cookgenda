package ch.morgias.cookgenda.android.ui.screens.common.calendar

import com.kizitonwose.calendar.core.Week
import com.kizitonwose.calendar.core.yearMonth
import java.time.DayOfWeek
import java.time.Month
import java.time.Year
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

fun YearMonth.displayText(short: Boolean = false): String {
    return "${this.month.displayText(short = short)} ${this.year}"
}

fun Month.displayText(short: Boolean = true): String {
    val style = if (short) TextStyle.SHORT else TextStyle.FULL
    return getDisplayName(style, Locale.ENGLISH)
}

fun DayOfWeek.displayText(uppercase: Boolean = false, narrow: Boolean = false): String {
    val style = if (narrow) TextStyle.NARROW else TextStyle.SHORT
    return getDisplayName(style, Locale.ENGLISH).let { value ->
        if (uppercase) value.uppercase(Locale.ENGLISH) else value
    }
}


fun getWeekPageTitle(week: Week): String {
    val firstDate = week.days.first().date
    val lastDate = week.days.last().date
    return when {
        firstDate.yearMonth == lastDate.yearMonth -> {
            firstDate.yearMonth.displayText()
        }

        firstDate.year == lastDate.year -> {
            "${firstDate.month.displayText(short = false)} - ${lastDate.yearMonth.displayText()}"
        }

        else -> {
            "${firstDate.yearMonth.displayText()} - ${lastDate.yearMonth.displayText()}"
        }
    }
}

fun Year.yearsUntil(other: Year) = ChronoUnit.YEARS.between(this, other)