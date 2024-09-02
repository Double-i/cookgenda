package ch.morgias.cookgenda.models.shopping;

import ch.morgias.cookgenda.models.food.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ShoppingListFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @ManyToOne
    private ShoppingList shoppingList;
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;
    private LocalDate planedDate;
    private Double quantity;

    private Boolean checked = false;
}
