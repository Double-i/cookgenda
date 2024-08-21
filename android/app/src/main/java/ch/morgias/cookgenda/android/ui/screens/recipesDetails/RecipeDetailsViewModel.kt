package ch.morgias.cookgenda.android.ui.screens.recipesDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.network.RecipeApi
import ch.morgias.cookgenda.android.network.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel : ViewModel() {
    private val _recipeUiState: MutableStateFlow<RequestState> =
        MutableStateFlow(RequestState.Loading)
    val uiState: StateFlow<RequestState> = _recipeUiState.asStateFlow()

    fun getRecipeDetail(id: Int) {
        viewModelScope.launch {
            _recipeUiState.value = try {
                Log.i("RecipeDetailsViewModel - getRecipeDetail", "données recette ")
                RequestState.Success(RecipeApi.retrofitService.getRecipe(id))
            } catch (ex: Exception) {
                Log.e("t", ex.message!!, ex.cause)
                RequestState.Error
            }
        }
    }
}