package ch.morgias.cookgenda.android.ui.screens.planning

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ch.morgias.cookgenda.android.R
import ch.morgias.cookgenda.android.models.PlanedRecipe
import ch.morgias.cookgenda.android.models.PlaningDay
import ch.morgias.cookgenda.android.network.RequestState
import ch.morgias.cookgenda.android.ui.screens.common.ErrorLoading
import ch.morgias.cookgenda.android.ui.screens.common.Loading
import ch.morgias.cookgenda.android.ui.screens.recipesDetails.RecipeDetailsViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

typealias onAddMealCallback = (date: LocalDate) -> Unit
typealias onDeleteMealCallback = (planedRecipeId: Int) -> Unit


@Composable
fun DayPlaning(
    day: PlaningDay,
    planningMode: Boolean,
    onAddMeal: onAddMealCallback,
    onDeleteMeal: onDeleteMealCallback
) {
    Column(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = day.date.format(DateTimeFormatter.ofPattern("EEEE (dd.MM)")),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
        )
        day.recipes.forEach { r -> DayMealPlaning(recipe = r, onDeleteMeal) }
        Button(onClick = {
            onAddMeal(day.date.toLocalDate())
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = if (planningMode) "Planifier " else "Ajouter")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DayMealPlaning(recipe: PlanedRecipe, onDeleteMeal: onDeleteMealCallback) {
    var value by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(20)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape)
            .background(color = Color.Green)
            .height(79.dp)
            .combinedClickable(
                onClick = { },
                onLongClick = {
                    value = !value
                    Log.i("test", "VALUE $value")
                },
            )
    ) {

        Image(
            painter = painterResource(id = R.drawable.recipe),
            "Recipe image",
            modifier = Modifier
                .height(79.dp)
                .width(79.dp)
                .clip(RoundedCornerShape(14))
        )
        Column(
            modifier = Modifier
                .weight(10F)
                .padding(9.dp)
        ) {
            Text(recipe.name)
            Text(color = Color.Gray, text = "COUCOU1")
        }
        Column(
            modifier = Modifier
                .height(79.dp)
                .padding(9.dp), verticalArrangement = Arrangement.Center
        ) {
            Log.i("test", "VALUE")
            when (value) {
                true -> Icon(
                    Icons.AutoMirrored.Rounded.List,
                    contentDescription = "test",
                    modifier = Modifier.clickable {
                        onDeleteMeal(recipe.planedRecipeId)
                    }
                )

                false -> Icon(
                    Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = "test"
                )
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Planning(navController: NavHostController, viewModel: RecipeDetailsViewModel) {
    val pager = rememberPagerState(pageCount = { 5 })
    Column {
        var t = viewModel.selectedRecipe.observeAsState().value
        viewModel.selectedRecipe.observeAsState().value?.let {
            Text(
                text = "Ajouter la recette : ${it.name}"
            )
        }
        HorizontalPager(state = pager) { page ->
            viewModel.getPlannedRecipeForSpecificWeek(
                viewModel.selectedMonday.plusDays(page * 7L),
                viewModel.selectedMonday.plusDays(page * 7L + 6L)
            )
            when (val state = viewModel.planningUiState.collectAsState().value) {
                RequestState.Error -> ErrorLoading()
                RequestState.Loading -> Loading()
                is RequestState.Success<*> -> {
                    val days = (state as RequestState.Success<List<PlaningDay>>).result
                    Column {
                        Row {
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                items(days) { day ->

                                    DayPlaning(
                                        day = day,
                                        planningMode = viewModel.planningMode.observeAsState().value
                                            ?: false,
                                        onAddMeal = { date ->
                                            viewModel.selectedRecipe.value?.let {
                                                viewModel.addToPlaning(
                                                    it.id, date
                                                )
                                            }
                                        },
                                        onDeleteMeal = {
                                            viewModel.deletePlanedRecipe(it)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}