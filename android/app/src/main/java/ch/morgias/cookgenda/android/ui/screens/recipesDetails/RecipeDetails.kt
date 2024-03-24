package ch.morgias.cookgenda.android.ui.screens.recipesDetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    LaunchedEffect(recipeId) {
        recipeDetailViewModel.getRecipeDetail(recipeId) // Déclenche le fetch des données
        Log.i("RecipeDetails", "données recette ")
    }
    when (recipeDetailViewModel.recipeUiState) {
        RequestState.Error -> ErrorLoading()
        RequestState.Loading -> Loading()
        is RequestState.Success<*> -> {
            val details =
                (recipeDetailViewModel.recipeUiState as RequestState.Success<RecipeDetails>).result

            Row() {
                Image(painterResource(id = R.drawable.recipe), contentDescription = "Oui")
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