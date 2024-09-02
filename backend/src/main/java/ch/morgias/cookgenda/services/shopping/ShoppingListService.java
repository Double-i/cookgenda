package ch.morgias.cookgenda.services.shopping;


import ch.morgias.cookgenda.models.shopping.ShoppingList;
import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.EditShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.GeneratingShoppingListDto;

import java.util.List;

public interface ShoppingListService {

    ShoppingList generateShoppingList(GeneratingShoppingListDto generatingShoppingListDto);

    ShoppingList getShoppingListById(Long shoppingListId);

    ShoppingListFood getShoppingListFoodById(Long shoppingListFoodId);


    ShoppingListFood updateShoppingListFoodById(Long shoppingListFoodId, EditShoppingListFood editShoppingListFood);

    void deleteShoppingListFoodById(Long shoppingListFoodId);

    List<ShoppingList> getShoppingLists();
}
