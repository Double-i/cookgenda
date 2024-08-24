package ch.morgias.cookgenda.android.ui.screens.planning

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ch.morgias.cookgenda.android.R
import ch.morgias.cookgenda.android.models.PlaningDay
import ch.morgias.cookgenda.android.models.Recipe
import ch.morgias.cookgenda.android.network.RequestState
import ch.morgias.cookgenda.android.ui.screens.common.ErrorLoading
import ch.morgias.cookgenda.android.ui.screens.common.Loading
import ch.morgias.cookgenda.android.ui.screens.recipesDetails.RecipeDetailsViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

typealias onAddMealCallback = (recipeId: Int, date: LocalDate) -> Unit

@Composable
fun DayPlaning(
    day: PlaningDay,
    planningMode: Boolean,
    onAddMeal: onAddMealCallback
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
        day.recipes.forEach { r -> DayMealPlaning(recipe = r) }
        Button(onClick = {

            /**
             * TODO :- Envoyer une requête pour ajouter la planification
             *       - Receptionner reponse
             *       - mettre à jour (idéalement) le jour
             */
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = if (planningMode) "Planifier " else "Ajouter")
        }
    }
}

@Composable
fun DayMealPlaning(recipe: Recipe) {
    val shape = RoundedCornerShape(20)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape)
            .background(color = Color.Green)
            .height(79.dp)
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
            Icon(

                Icons.AutoMirrored.Rounded.ArrowForward,
                contentDescription = "test"
            )
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
                                        onAddMeal = { id, date ->
                                            viewModel.addToPlaning(id, date)

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