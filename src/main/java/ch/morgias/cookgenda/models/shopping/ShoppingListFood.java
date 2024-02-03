package ch.morgias.cookgenda.models.shopping;

import ch.morgias.cookgenda.models.food.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShoppingListFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    private ShoppingList shoppingList;

    @ManyToOne
    private Food food;

    private Double quantity;
}
