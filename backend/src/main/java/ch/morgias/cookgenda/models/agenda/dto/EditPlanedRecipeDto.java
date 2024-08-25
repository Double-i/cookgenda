package ch.morgias.cookgenda.models.agenda.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EditPlanedRecipeDto {
    private LocalDateTime date;
    private Long recipeId;

}
