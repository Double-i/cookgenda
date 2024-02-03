package ch.morgias.cookgenda.controllers;

import ch.morgias.cookgenda.infrastructure.Routes;
import ch.morgias.cookgenda.models.agenda.dto.EditPlanedRecipeDto;
import ch.morgias.cookgenda.models.agenda.dto.PlanedRecipeDto;
import ch.morgias.cookgenda.models.agenda.dto.mappers.PlanedRecipeMapper;
import ch.morgias.cookgenda.services.agenda.PlanedRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PlanedRecipeController {

    private final PlanedRecipeService planedRecipeService;

    @PostMapping(Routes.PLANED_RECIPE_CREATE)
    public PlanedRecipeDto plannedRecipe(@PathVariable Long recipeId, @RequestBody EditPlanedRecipeDto planedRecipeDto) {
        return PlanedRecipeMapper.INSTANCE.toPlanedRecipeDto(planedRecipeService.planRecipe(recipeId, planedRecipeDto));
    }

    @GetMapping(Routes.PLANED_RECIPE_INDEX_PERIOD)
    public Collection<PlanedRecipeDto> getPlannedRecipe(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        return PlanedRecipeMapper.INSTANCE.toPlanedRecipeDtoList(planedRecipeService.findPlanedRecipeByPeriod(from, to));
    }
}
