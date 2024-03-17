package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListFood, Long> {
}
