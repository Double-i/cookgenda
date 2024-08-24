package ch.morgias.cookgenda.controllers;

import ch.morgias.cookgenda.infrastructure.Routes;
import ch.morgias.cookgenda.models.agenda.PlanedRecipe;
import ch.morgias.cookgenda.models.agenda.PlaningDayDto;
import ch.morgias.cookgenda.models.agenda.dto.EditPlanedRecipeDto;
import ch.morgias.cookgenda.models.agenda.dto.PlanedRecipeDto;
import ch.morgias.cookgenda.models.agenda.dto.mappers.PlanedRecipeMapper;
import ch.morgias.cookgenda.services.agenda.PlanedRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class PlanedRecipeController {

    private final PlanedRecipeService planedRecipeService;

    @PostMapping(Routes.PLANED_RECIPE_CREATE)
    public PlanedRecipeDto plannedRecipe(@PathVariable Long recipeId, @RequestBody EditPlanedRecipeDto planedRecipeDto) {
        return PlanedRecipeMapper.INSTANCE.toPlanedRecipeDto(planedRecipeService.planRecipe(recipeId, planedRecipeDto));
    }

    @GetMapping(Routes.PLANED_RECIPE_INDEX_PERIOD)
    public Collection<PlaningDayDto> getPlannedRecipe(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime from,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to) {
        Collection<PlanedRecipe> t = planedRecipeService.findPlanedRecipeByPeriod(from, to);
        // TODO déplacer dans un endroit plus approprié (mapper ? service)
        LocalDate d = LocalDate.from(from);
        Map<Long, List<PlanedRecipe>> map = new HashMap<>();
        for (PlanedRecipe t2 : t) {
            while (d.isAfter(to.toLocalDate())) {
                // Create a list if not exists (we want empty list for empty days)
                if (!map.containsKey(d.toEpochDay())) {
                    map.put(d.toEpochDay(), new ArrayList<>());
                }
                if (!t2.getPlanedDate().toLocalDate().equals(d)) {
                    continue;
                }
                map.get(d.toEpochDay()).add(t2);
            }
            d = d.plusDays(1);
        }

        return PlanedRecipeMapper.INSTANCE.toPlanedRecipeDtoV2List(map);
    }
}