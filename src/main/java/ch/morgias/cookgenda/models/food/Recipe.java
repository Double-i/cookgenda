package ch.morgias.cookgenda.models.food;

import ch.morgias.cookgenda.models.food.dto.AddFoodToRecipeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<RecipeFood> recipeFoods = new HashSet<>();

    public void addFoodRecipe(AddFoodToRecipeDto addFoodToRecipeDto) {

    }
}
