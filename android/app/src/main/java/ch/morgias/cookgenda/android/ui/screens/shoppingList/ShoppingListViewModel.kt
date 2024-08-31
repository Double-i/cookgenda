package ch.morgias.cookgenda.android.ui.screens.shoppingList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.network.RequestState
import ch.morgias.cookgenda.android.network.ShoppingListApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {
    private val _shoppingListUiState: MutableStateFlow<RequestState> =
        MutableStateFlow(RequestState.Loading)
    val shoppingListUiState: StateFlow<RequestState> = _shoppingListUiState.asStateFlow()
    init {
        getShoppingListsList()
    }

    private fun getShoppingListsList() {
        viewModelScope.launch {
            _shoppingListUiState.value = try {
                RequestState.Success(ShoppingListApi.retrofitService.getShoppingListResumes())
            } catch (err: Exception) {
                RequestState.Error
            }
        }
    }
}