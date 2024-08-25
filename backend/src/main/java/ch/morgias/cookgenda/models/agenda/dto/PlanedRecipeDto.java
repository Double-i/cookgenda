package ch.morgias.cookgenda.models.agenda.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlanedRecipeDto {
   private Long recipeId;
   private Long planedRecipeId;
   private LocalDateTime date;
   private String name;
}
