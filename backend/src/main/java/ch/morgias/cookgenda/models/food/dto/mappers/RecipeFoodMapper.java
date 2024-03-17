package ch.morgias.cookgenda.models.food.dto.mappers;

import ch.morgias.cookgenda.models.food.Recipe;
import ch.morgias.cookgenda.models.food.RecipeFood;
import ch.morgias.cookgenda.models.food.dto.AddFoodToRecipeDto;
import ch.morgias.cookgenda.models.food.dto.RecipeNameDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface RecipeFoodMapper {
    RecipeFoodMapper INSTANCE = Mappers.getMapper(RecipeFoodMapper.class);
    RecipeFood toRecipeFood(AddFoodToRecipeDto addFoodToRecipeDto);
    RecipeNameDto toRecipeNameDtoList(Recipe recipe);
    Collection<RecipeNameDto> toRecipeNameDtoList(Collection<Recipe> allRecipes);
}
