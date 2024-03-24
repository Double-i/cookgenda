package ch.morgias.cookgenda.android.ui.screens.recipesDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.network.RecipeApi
import ch.morgias.cookgenda.android.network.RequestState
import kotlinx.coroutines.launch

class RecipeDetailsViewModel : ViewModel() {
    var recipeUiState: RequestState by mutableStateOf(RequestState.Loading)
        private set


    fun getRecipeDetail(id: Int) {
        viewModelScope.launch {
            recipeUiState = try {
                RequestState.Success(RecipeApi.retrofitService.getRecipe(id))
            } catch (ex: Exception) {
                Log.e("t", ex.message!!, ex.cause)
                RequestState.Error
            }
            Log.i("VM", recipeUiState.toString())
        }
    }
}