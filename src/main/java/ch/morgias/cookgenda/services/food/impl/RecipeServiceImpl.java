package ch.morgias.cookgenda.services.food.impl;

import ch.morgias.cookgenda.exceptions.ResourceNotFoundException;
import ch.morgias.cookgenda.models.food.Food;
import ch.morgias.cookgenda.models.food.Recipe;
import ch.morgias.cookgenda.models.food.RecipeFood;
import ch.morgias.cookgenda.models.food.dto.AddFoodToRecipeDto;
import ch.morgias.cookgenda.models.food.dto.RecipeEditDto;
import ch.morgias.cookgenda.models.food.dto.mappers.RecipeFoodMapper;
import ch.morgias.cookgenda.models.food.dto.mappers.RecipeMapper;
import ch.morgias.cookgenda.repositories.FoodRepository;
import ch.morgias.cookgenda.repositories.RecipeRepository;
import ch.morgias.cookgenda.services.food.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final FoodRepository foodRepository;

    @Override
    public Collection<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public void createRecipe(RecipeEditDto recipeDto) {
        Recipe recipe = RecipeMapper.INSTANCE.toEntity(recipeDto);
        recipeRepository.save(recipe);
    }

    @Override
    public void addFoodToRecipe(Long recipeId, AddFoodToRecipeDto addFoodToRecipeDto) {
        Recipe recipe = findRecipeById(recipeId);
        Food food = foodRepository.findById(addFoodToRecipeDto.getFoodId()).orElseThrow(ResourceNotFoundException::new);

        RecipeFood recipeFood = RecipeFoodMapper.INSTANCE.toRecipeFood(addFoodToRecipeDto);
        recipeFood.setFood(food);
        recipeFood.setRecipe(recipe);

        recipe.getRecipeFoods().add(recipeFood);
        food.getRecipeFoods().add(recipeFood);

        recipeRepository.save(recipe);
    }

    @Override
    public Recipe findRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteRecipeById(Long recipeId) {
        recipeRepository.delete(findRecipeById(recipeId));
    }
}
