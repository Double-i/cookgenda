package ch.morgias.cookgenda.controllers;

import ch.morgias.cookgenda.infrastructure.Routes;
import ch.morgias.cookgenda.models.food.dto.RecipeNameDto;
import ch.morgias.cookgenda.models.food.dto.mappers.RecipeFoodMapper;
import ch.morgias.cookgenda.services.food.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping(Routes.RECIPE_INDEX)
    public Collection<RecipeNameDto> getFoodNames() {
        return RecipeFoodMapper.INSTANCE.toRecipeNameDtoList(recipeService.getAllRecipes());
    }
}
