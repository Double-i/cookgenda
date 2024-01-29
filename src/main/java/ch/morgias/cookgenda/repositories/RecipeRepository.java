package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.food.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
