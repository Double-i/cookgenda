package ch.morgias.cookgenda.android.ui.screens.shoppingList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.morgias.cookgenda.android.models.ShoppingList
import ch.morgias.cookgenda.android.models.ShoppingListItem
import ch.morgias.cookgenda.android.models.ShoppingListWithFoodListByCategory
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
    /*  val shoppingListFoodByCategory =
          _shoppingListUiState.transform<RequestState, ShoppingListWithFoodListByCategory> { requestState ->
              val map = HashMap<String, MutableList<ShoppingListItem>>()
              when (requestState) {
                  RequestState.Error, RequestState.Loading -> map
                  is RequestState.Success<*> -> {
                      val info = requestState.result as ShoppingList
                      info.shoppingListFoods.forEach {
                          var list = map["CAT"]
                          if (list == null) {
                              list = ArrayList()
                              map["CAT"] = list
                          }
                          list.add(
                              ShoppingListItem(
                                  it.id,
                                  it.name,
                                  it.quantity,
                                  it.planedDate,
                                  it.checked
                              )
                          )
                      }
                  }
              }
          }*/

    fun getShoppingList(shoppingListId: Int) {
        viewModelScope.launch {
            _shoppingListUiState.value = try {
                val result = ShoppingListApi.retrofitService.getShoppingList(shoppingListId)
                RequestState.Success(result)
            } catch (err: Exception) {
                RequestState.Error
            }
        }
    }

    fun transform(info: ShoppingList): ShoppingListWithFoodListByCategory {
        val map = hashMapOf<String, MutableList<ShoppingListItem>>()
        info.shoppingListFoods.forEach {
            var list = map[it.name]
            if (list == null) {
                list = ArrayList()
                map[it.name] = list
            }
            list.add(
                ShoppingListItem(
                    it.id,
                    it.name,
                    it.quantity,
                    it.planedDate,
                    it.checked
                )
            )
        }
        return ShoppingListWithFoodListByCategory(info.id, info.fromDate, info.toDate, map);
    }
}