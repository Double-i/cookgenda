package ch.morgias.cookgenda.android.ui.screens.shoppingList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.network.RequestState
import ch.morgias.cookgenda.android.network.ShoppingListApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

typealias GenerateShoppingSuccessCallback = (id: Long) -> Unit

class ShoppingListsViewModel : ViewModel() {
    private val _shoppingListsUiState: MutableStateFlow<RequestState> =
        MutableStateFlow(RequestState.Loading)
    val shoppingListUiState: StateFlow<RequestState> = _shoppingListsUiState.asStateFlow()
    init {
        getShoppingListsList()
    }

    private fun getShoppingListsList() {
        viewModelScope.launch {
            _shoppingListsUiState.value = try {
                RequestState.Success(ShoppingListApi.retrofitService.getShoppingListResumes())
            } catch (err: Exception) {
                RequestState.Error
            }
        }
    }
    fun generateNewShoppingList(
        startDate: LocalDate,
        endDate: LocalDate,
        callback: GenerateShoppingSuccessCallback
    ) {
        viewModelScope.launch {
            try {
                val t = ShoppingListApi.retrofitService.generateShoppingList(
                    startDate,
                    endDate

                )
                callback(t.id)

            } catch (err: Exception) {
                RequestState.Error
            }
        }
    }
}