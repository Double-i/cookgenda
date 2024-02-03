package ch.morgias.cookgenda.models.food;

import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "food",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RecipeFood> recipeFoods = new HashSet<>();
    @OneToMany(
            mappedBy = "food",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ShoppingListFood> shoppingFoods = new HashSet<>();
    private String name;
}
