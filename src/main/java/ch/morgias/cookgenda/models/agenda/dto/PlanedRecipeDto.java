package ch.morgias.cookgenda.models.agenda.dto;

import ch.morgias.cookgenda.models.food.dto.RecipeNameDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlanedRecipeDto {
    Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime planedDate;

    RecipeNameDto recipe;
}
