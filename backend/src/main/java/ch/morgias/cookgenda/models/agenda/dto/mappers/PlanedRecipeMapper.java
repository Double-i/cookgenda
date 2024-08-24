package ch.morgias.cookgenda.models.agenda.dto.mappers;

import ch.morgias.cookgenda.models.agenda.PlanedRecipe;
import ch.morgias.cookgenda.models.agenda.PlanedRecipeV2Dto;
import ch.morgias.cookgenda.models.agenda.PlaningDayDto;
import ch.morgias.cookgenda.models.agenda.dto.EditPlanedRecipeDto;
import ch.morgias.cookgenda.models.agenda.dto.PlanedRecipeDto;
import ch.morgias.cookgenda.models.food.dto.mappers.RecipeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mapper(uses = {RecipeMapper.class})
public interface PlanedRecipeMapper {
    PlanedRecipeMapper INSTANCE = Mappers.getMapper(PlanedRecipeMapper.class);
    PlanedRecipe toPlanedRecipe(EditPlanedRecipeDto editPlanedRecipeDto);
    PlanedRecipeDto toPlanedRecipeDto(PlanedRecipe planedRecipe);

    @Mapping(source = "recipe.name", target = "name")
    PlanedRecipeV2Dto toPlanedRecipeDtoV2(PlanedRecipe planedRecipe);
    Collection<PlanedRecipeDto> toPlanedRecipeDtoList(Collection<PlanedRecipe> planedRecipes);
    default Collection<PlaningDayDto> toPlanedRecipeDtoV2List(Map<Long, List<PlanedRecipe>> planedRecipes){
        List<PlaningDayDto> result = new ArrayList<>();
        for (Map.Entry<Long, List<PlanedRecipe>> planingDay : planedRecipes.entrySet()) {
            LocalDate date = LocalDate.ofEpochDay(planingDay.getKey());
            PlaningDayDto dto = new PlaningDayDto();
            dto.setDate(date.atStartOfDay());
            dto.setRecipes(planingDay.getValue().stream().map(INSTANCE::toPlanedRecipeDtoV2).toList());
            result.add(dto);
        }
        return result;
    }
}
