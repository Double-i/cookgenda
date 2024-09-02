package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.shopping.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    @Query("select sl from ShoppingList sl " +
            "left join fetch sl.shoppingListFoods")
    List<ShoppingList> findAllWithFoodList();

    @Query("select sl from ShoppingList sl " +
            "left join fetch sl.shoppingListFoods slf " +
            "left join fetch  slf.food " +
            "where sl.id = :shoppingListId")
    Optional<ShoppingList> findByIdWithFoodItems(Long shoppingListId);
}
