package ch.morgias.cookgenda.services.agenda.impl;

import ch.morgias.cookgenda.exceptions.ResourceNotFoundException;
import ch.morgias.cookgenda.models.agenda.PlanedRecipe;
import ch.morgias.cookgenda.models.agenda.dto.EditPlanedRecipeDto;
import ch.morgias.cookgenda.models.agenda.dto.mappers.PlanedRecipeMapper;
import ch.morgias.cookgenda.models.food.Recipe;
import ch.morgias.cookgenda.repositories.PlanedRecipeRepository;
import ch.morgias.cookgenda.services.agenda.PlanedRecipeService;
import ch.morgias.cookgenda.services.food.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanedRecipeServiceImpl implements PlanedRecipeService {
    private final PlanedRecipeRepository planedRecipeRepository;
    private final RecipeService recipeService;

    @Override
    public PlanedRecipe planRecipe(Long recipeId, EditPlanedRecipeDto editPlanedRecipeDto) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        PlanedRecipe planedRecipe = PlanedRecipeMapper.INSTANCE.toPlanedRecipe(editPlanedRecipeDto);
        planedRecipe.setRecipe(recipe);
        planedRecipeRepository.save(planedRecipe);
        return planedRecipe;
    }

    @Override
    public PlanedRecipe findPlanedRecipeById(Long planedRecipeId) {
        return planedRecipeRepository.findById(planedRecipeId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<PlanedRecipe> findPlanedRecipeByPeriod(LocalDateTime to, LocalDateTime from) {
        return planedRecipeRepository.findByPeriod(to, from);
    }


    @Override
    public void deletePlanedRecipeById(Long recipeId) {

    }

    @Override
    public Collection<PlanedRecipe> findPlanedRecipeByPeriodWithFoods(LocalDateTime from, LocalDateTime to) {
        return planedRecipeRepository.findByPeriodWithFoods(from, to);
    }
}
