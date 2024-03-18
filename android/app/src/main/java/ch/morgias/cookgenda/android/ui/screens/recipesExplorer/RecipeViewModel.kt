package ch.morgias.cookgenda.android.ui.screens.recipesExplorer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.network.RecipeApi
import ch.morgias.cookgenda.android.network.RequestState
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    var RecipeUiState: RequestState by mutableStateOf(RequestState.Loading)
        private set

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            RecipeUiState = try {
                RequestState.Success(RecipeApi.retrofitService.getRecipes())
            } catch (ex: Exception) {
                RequestState.Error
            }
        }
    }
}