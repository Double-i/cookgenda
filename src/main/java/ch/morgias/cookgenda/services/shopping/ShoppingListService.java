package ch.morgias.cookgenda.services.shopping;


import ch.morgias.cookgenda.models.shopping.ShoppingList;

import java.time.LocalDateTime;

public interface ShoppingListService {

    ShoppingList generateShoppingList(LocalDateTime from, LocalDateTime to);

    ShoppingList getShoppingListById(Long shoppingListId);

    void checkShoppingItem(Long shoppingItemId);

}
