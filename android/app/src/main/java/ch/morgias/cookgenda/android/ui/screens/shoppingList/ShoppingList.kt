package ch.morgias.cookgenda.android.ui.screens.shoppingList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ch.morgias.cookgenda.android.models.ShoppingList
import ch.morgias.cookgenda.android.network.RequestState


@Composable
fun ShoppingList(viewModel: ShoppingListViewModel, shoppingListId: Int) {
    LaunchedEffect(shoppingListId) {
        viewModel.getShoppingList(shoppingListId)

    }


    when (val state = viewModel.shoppingListUiState.collectAsState().value) {
        RequestState.Error -> RequestState.Error
        RequestState.Loading -> RequestState.Loading
        is RequestState.Success<*> -> {
            /* val listInfo =
                 viewModel.shoppingListFoodByCategory.collectAsState(initial = null).value ?: return*/
            val listInfo = viewModel.transform(state.result as ShoppingList)
            Column(modifier = Modifier.padding(4.dp)) {
                Text("Course du date1 au date2")
                LazyColumn {
                    listInfo.shoppingListFoods.keys.toList().forEach {
                        item {
                            Text("Titre")
                        }
                        items(listInfo.shoppingListFoods[it]!!) { foodItem ->
                            Row {
                                Column {
                                    Text(foodItem.name)
                                    Text(foodItem.quantity.toString())
                                }
                                Checkbox(checked = foodItem.checked, onCheckedChange = {
                                    viewModel.checkShoppingListFoodWithId(foodItem.id, it)
                                })
                            }

                        }
                    }
                }
            }
        }
    }
}