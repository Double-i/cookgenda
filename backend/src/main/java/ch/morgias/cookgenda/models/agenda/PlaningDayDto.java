package ch.morgias.cookgenda.models.agenda;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PlaningDayDto {
    private LocalDateTime date;
    private List<PlanedRecipeV2Dto> recipes;
}
