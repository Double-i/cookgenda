package ch.morgias.cookgenda.models.shopping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShoppingListFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

}
