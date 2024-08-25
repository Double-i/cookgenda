package ch.morgias.cookgenda.models.agenda;

import ch.morgias.cookgenda.models.agenda.dto.PlanedRecipeDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PlaningDayDto {
    private LocalDateTime date;
    private List<PlanedRecipeDto> recipes;
}
