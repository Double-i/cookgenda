package ch.morgias.cookgenda.models.food;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RecipeFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name="foodId")
    private Food food;
    @ManyToOne
    @JoinColumn(name="recipeId")
    private Recipe recipe;
}
