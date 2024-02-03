package ch.morgias.cookgenda.models.agenda.dto.mappers;

import ch.morgias.cookgenda.models.agenda.PlanedRecipe;
import ch.morgias.cookgenda.models.agenda.dto.EditPlanedRecipeDto;
import ch.morgias.cookgenda.models.agenda.dto.PlanedRecipeDto;
import ch.morgias.cookgenda.models.food.dto.mappers.RecipeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(uses = {RecipeMapper.class})
public interface PlanedRecipeMapper {
    PlanedRecipeMapper INSTANCE = Mappers.getMapper(PlanedRecipeMapper.class);

    PlanedRecipe toPlanedRecipe(EditPlanedRecipeDto editPlanedRecipeDto);

    PlanedRecipeDto toPlanedRecipeDto(PlanedRecipe planedRecipe);

    Collection<PlanedRecipeDto> toPlanedRecipeDtoList(Collection<PlanedRecipe> planedRecipes);
}
