package ch.morgias.cookgenda.models.agenda;

import ch.morgias.cookgenda.models.food.Recipe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Entity
@Getter
@Setter
public class PlanedRecipe {

    @ManyToOne
    private Recipe recipe;

    Calendar date;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
