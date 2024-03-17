package ch.morgias.cookgenda.services.agenda;

import ch.morgias.cookgenda.models.agenda.PlanedRecipe;
import ch.morgias.cookgenda.models.agenda.dto.EditPlanedRecipeDto;

import java.time.LocalDateTime;
import java.util.Collection;

public interface PlanedRecipeService {

    PlanedRecipe planRecipe(Long recipeId, EditPlanedRecipeDto recipeDto);

    PlanedRecipe findPlanedRecipeById(Long recipeId);

    Collection<PlanedRecipe> findPlanedRecipeByPeriod(LocalDateTime to, LocalDateTime from);

    void deletePlanedRecipeById(Long recipeId);

    Collection<PlanedRecipe> findPlanedRecipeByPeriodWithFoods(LocalDateTime from, LocalDateTime to);
}
