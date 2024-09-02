package ch.morgias.cookgenda.android.ui.screens.shoppingList

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ch.morgias.cookgenda.android.Screen
import ch.morgias.cookgenda.android.models.ShoppingListResume
import ch.morgias.cookgenda.android.network.RequestState
import ch.morgias.cookgenda.android.ui.screens.common.ErrorLoading
import ch.morgias.cookgenda.android.ui.screens.common.Loading
import ch.morgias.cookgenda.android.ui.screens.common.calendar.ContinuousSelectionHelper.getSelection
import ch.morgias.cookgenda.android.ui.screens.common.calendar.DateSelection
import ch.morgias.cookgenda.android.ui.screens.common.calendar.displayText
import ch.morgias.cookgenda.android.utils.dateFormatter
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private val primaryColor = Color.Black.copy(alpha = 0.9f)
private val selectionColor = primaryColor
private val continuousSelectionColor = Color.LightGray.copy(alpha = 0.3f)


private val rangeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
fun dateRangeDisplayText(startDate: LocalDate, endDate: LocalDate): String {
    return "Selected: ${rangeFormatter.format(startDate)} - ${rangeFormatter.format(endDate)}"
}

@Composable
private fun Day(
    day: CalendarDay,
    today: LocalDate,
    selection: DateSelection,
    onClick: (CalendarDay) -> Unit,
) {
    val textColor = Color.Black
    val dayColor: Color = when {
        selection.startDate == day.date || selection.endDate == day.date -> Color.Blue
        selection.startDate != null && selection.endDate != null && !day.date.isBefore(selection.startDate) && !day.date.isAfter(
            selection.endDate
        )
        -> Color.Yellow

        else -> Color.Transparent // or any default color if none of the conditions are met
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f) // This is important for square-sizing!
            .clickable(
                enabled = day.position == DayPosition.MonthDate && day.date >= today,
                onClick = { onClick(day) },
            )
            .background(dayColor),

        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun ShoppingLists(navController: NavHostController, viewModel: ShoppingListsViewModel) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val today = remember { LocalDate.now() }
    var selection by remember { mutableStateOf(DateSelection()) }
    var hasSelection by remember { mutableStateOf(false) }
    val daysOfWeek = remember { daysOfWeek() }
    var visible by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Liste des listes de courses", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalDivider()
        AnimatedVisibility(visible) {
            Column {
                val state = rememberCalendarState(
                    startMonth = startMonth,
                    endMonth = endMonth,
                    firstVisibleMonth = currentMonth,
                    firstDayOfWeek = daysOfWeek.first(),
                )
                CalendarTop(
                    daysOfWeek = daysOfWeek,
                    selection = selection,
                    clearDates = { selection = DateSelection() }
                )
                HorizontalCalendar(
                    state = state,
                    contentPadding = PaddingValues(bottom = 100.dp),
                    dayContent = { value ->
                        Day(
                            value,
                            today = today,
                            selection = selection,
                        ) { day ->

                            selection = getSelection(
                                clickedDate = day.date,
                                dateSelection = selection,
                            )
                            hasSelection = selection.startDate != null && selection.endDate != null

                        }
                    },
                    monthHeader = { month -> MonthHeader(month) },
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Button(
                        onClick = {
                            if (selection.startDate == null || selection.endDate == null) {
                                return@Button;
                            }
                            viewModel.generateNewShoppingList(
                                selection.startDate!!,
                                selection.endDate!!
                            ) {
                                navController.navigate(
                                    Screen.ShoppingListScreen.withShoppingListId(
                                        it
                                    )
                                )
                            }

                        },
                        enabled = hasSelection,
                        modifier = Modifier.weight(1F)

                    ) {
                        Text(text = "Générer")
                    }
                    Button(onClick = {
                        visible = false
                    }, modifier = Modifier.weight(1F)) {
                        Text(text = "Annuler")

                    }
                }
            }
        }

        AnimatedVisibility(!visible) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Générer une nouvelle liste de course")
                Button(onClick = {
                    Log.i("test", "OCOUCOU")
                    visible = !visible
                }) {
                    Text(text = "Générer")
                }
            }
        }
        HorizontalDivider()

        Text("Listes de course en cours", style = MaterialTheme.typography.titleMedium)

        when (val state = viewModel.shoppingListUiState.collectAsState().value) {
            RequestState.Error -> ErrorLoading()
            RequestState.Loading -> Loading()
            is RequestState.Success<*> -> {

                LazyColumn(modifier = Modifier.padding(4.dp)) {
                    items((state as RequestState.Success<List<ShoppingListResume>>).result) { recipe ->
                        Row(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            Icon(
                                Icons.AutoMirrored.Rounded.List,
                                contentDescription = "test",
                                modifier = Modifier.weight(1F)
                            )
                            Column(modifier = Modifier.weight(5F)) {
                                Text(
                                    "${recipe.fromDate.format(dateFormatter)} - ${
                                        recipe.toDate.format(
                                            dateFormatter
                                        )
                                    }",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    "${recipe.numberOfCheckedFoods} / ${recipe.shoppingListSize}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MonthHeader(calendarMonth: CalendarMonth) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = calendarMonth.yearMonth.displayText(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun CalendarTop(
    modifier: Modifier = Modifier,
    daysOfWeek: List<DayOfWeek>,
    selection: DateSelection,
    clearDates: () -> Unit,
) {
    Column(modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
            ) {
                for (dayOfWeek in daysOfWeek) {
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        text = dayOfWeek.displayText(),
                        fontSize = 15.sp,
                    )
                }
            }
        }
        HorizontalDivider()
    }
}