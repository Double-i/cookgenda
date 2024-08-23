package ch.morgias.cookgenda.android.ui.screens.recipesDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ch.morgias.cookgenda.android.R
import ch.morgias.cookgenda.android.Screen
import ch.morgias.cookgenda.android.models.RecipeDetails
import ch.morgias.cookgenda.android.network.RequestState
import ch.morgias.cookgenda.android.ui.screens.common.ErrorLoading
import ch.morgias.cookgenda.android.ui.screens.common.Loading
import coil.compose.AsyncImage


@Composable
fun RecipeDetails(
    navController: NavController,
    recipeId: Int,
    recipeDetailViewModel: RecipeDetailsViewModel
) {
    LaunchedEffect(recipeDetailViewModel) {
        recipeDetailViewModel.getRecipeDetail(recipeId) // Déclenche le fetch des données
    }
    when (val state = recipeDetailViewModel.uiState.collectAsState().value) {
        RequestState.Error -> ErrorLoading()
        RequestState.Loading -> Loading()
        is RequestState.Success<*> -> {
            val details =
                (state as RequestState.Success<RecipeDetails>).result
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Row(modifier = Modifier.background(Color.Green)) {
                    AsyncImage(
                        contentScale = ContentScale.FillWidth,
                        placeholder = painterResource(R.drawable.recipe),
                        modifier = Modifier.fillMaxWidth(),
                        model = details.image,
                        contentDescription = details.name
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row() {
                        Text(details.name, style = MaterialTheme.typography.titleLarge)
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = {
                            recipeDetailViewModel.selectRecipe(details)
                            navController.navigate(Screen.PlanningListScreen.route)
                        }) {
                            Text(text = "Ajouter au planning")
                        }
                    }
                    Text(text = "Ingrédients", style = MaterialTheme.typography.titleMedium)
                    Row() {
                        Column() {
                            details.recipeFoods.map {
                                Row {
                                    Text(it.quantity.toString(), modifier = Modifier.weight(.3F))
                                    Text(it.foodName, modifier = Modifier.weight(.7F))

                                }
                            }
                        }
                    }
                    Row() {
                        Text(details.description ?: "", textAlign = TextAlign.Justify)
                    }
                }
            }
        }
    }
}