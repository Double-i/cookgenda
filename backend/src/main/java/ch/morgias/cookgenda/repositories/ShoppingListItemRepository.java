package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListFood, Long> {
    @Query("select slf from ShoppingListFood  slf " +
            "inner join fetch slf.food f " +
            "where slf.id = :shoppingListFoodId")
    Optional<ShoppingListFood> findByIdWithFood(Long shoppingListFoodId);
}
