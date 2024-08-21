package ch.morgias.cookgenda.android.ui.screens.recipesDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ch.morgias.cookgenda.android.R
import ch.morgias.cookgenda.android.models.RecipeDetails
import ch.morgias.cookgenda.android.network.RequestState
import ch.morgias.cookgenda.android.ui.screens.common.ErrorLoading
import ch.morgias.cookgenda.android.ui.screens.common.Loading


@Composable
@Preview
fun RecipeDetails(recipeId: Int, recipeDetailViewModel: RecipeDetailsViewModel) {
    LaunchedEffect(recipeDetailViewModel) {
        recipeDetailViewModel.getRecipeDetail(recipeId) // Déclenche le fetch des données
    }
    when (val state = recipeDetailViewModel.uiState.collectAsState().value) {
        RequestState.Error -> ErrorLoading()
        RequestState.Loading -> Loading()
        is RequestState.Success<*> -> {
            val details =
                (state as RequestState.Success<RecipeDetails>).result
            Row(modifier = Modifier.background(Color.Green)) {
                Image(
                    painterResource(id = R.drawable.recipe),
                    contentDescription = "Oui",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row() {
                Text(details.name)
            }
            Row() {
                Column() {
                    Text("Ingredient1")
                    Text("Ingredient1")
                    Text("Ingredient1")
                }
            }
            Row() {
                Text(stringResource(R.string.lorem_medium))
            }
        }
    }
}