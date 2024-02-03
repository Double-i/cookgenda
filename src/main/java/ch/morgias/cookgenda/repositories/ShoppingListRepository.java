package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.shopping.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
}
