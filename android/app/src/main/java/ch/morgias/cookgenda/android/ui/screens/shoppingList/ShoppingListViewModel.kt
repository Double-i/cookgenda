package ch.morgias.cookgenda.android.ui.screens.shoppingList

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.network.RecipeApi
import ch.morgias.cookgenda.android.network.RequestState
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {
    var ShoppingListUiState: RequestState by mutableStateOf(RequestState.Loading)
        private set

    init {
        getShoppingListsList()
    }

    private fun getShoppingListsList() {
        viewModelScope.launch {

        }
    }
}