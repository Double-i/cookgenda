package ch.morgias.cookgenda.services.food;

import ch.morgias.cookgenda.models.food.Recipe;
import ch.morgias.cookgenda.models.food.dto.AddFoodToRecipeDto;
import ch.morgias.cookgenda.models.food.dto.RecipeEditDto;

import java.util.Collection;
import java.util.List;

public interface RecipeService {
    Collection<Recipe> getAllRecipes();

    void createRecipe(RecipeEditDto recipeDto);

    void addFoodToRecipe(Long recipeId, AddFoodToRecipeDto addFoodToRecipeDto);

    Recipe findRecipeById(Long recipeId);

    void deleteRecipeById(Long recipeId);

    Collection<Recipe> findAllRecipeByIdWithFoods(List<Long> recipeIds);

    Recipe getRecipeById(Long recipeId);
}
