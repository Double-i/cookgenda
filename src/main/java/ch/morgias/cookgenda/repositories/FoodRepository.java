package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
