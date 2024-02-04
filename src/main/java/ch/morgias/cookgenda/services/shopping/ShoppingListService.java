package ch.morgias.cookgenda.services.shopping;


import ch.morgias.cookgenda.models.shopping.ShoppingList;
import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.EditShoppingListFood;

import java.time.LocalDateTime;

public interface ShoppingListService {

    ShoppingList generateShoppingList(LocalDateTime from, LocalDateTime to);

    ShoppingList getShoppingListById(Long shoppingListId);

    ShoppingListFood getShoppingListFoodById(Long shoppingListFoodId);


    ShoppingListFood updateShoppingListFoodById(Long shoppingListFoodId, EditShoppingListFood editShoppingListFood);
}
