package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.shopping.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    @Query("select sl from ShoppingList sl " +
            "left join fetch sl.shoppingListFoods")
    List<ShoppingList> findAllWithFoodList();
}
