package ch.morgias.cookgenda.android.ui.screens.recipesDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.models.RecipeDetails
import ch.morgias.cookgenda.android.network.RecipeApi
import ch.morgias.cookgenda.android.network.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeDetailsViewModel : ViewModel() {
    private var _selectedRecipe: MutableLiveData<RecipeDetails> = MutableLiveData()
    val planningMode: LiveData<Boolean> = _selectedRecipe.switchMap { recipe ->
        MutableLiveData(recipe != null)
    }
    var selectedRecipe: LiveData<RecipeDetails?> = _selectedRecipe
    private val _recipeUiState: MutableStateFlow<RequestState> =
        MutableStateFlow(RequestState.Loading)
    val uiState: StateFlow<RequestState> = _recipeUiState.asStateFlow()
    fun selectRecipe(recipe: RecipeDetails) {
        _selectedRecipe.value = recipe
    }

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