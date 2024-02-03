package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.food.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("select r from Recipe r " +
            "left join fetch r.recipeFoods rf " +
            "left join fetch rf.food f " +
            "where r.id in :recipeIds")
    Collection<Recipe> findAllByIdWithFoods(List<Long> recipeIds);
}
