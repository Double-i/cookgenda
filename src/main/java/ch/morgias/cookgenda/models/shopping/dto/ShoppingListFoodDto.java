package ch.morgias.cookgenda.models.shopping.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingListFoodDto {
    private Long id;
    private Long foodId;
    private String name;
    private Double quantity;
    private Boolean checked;
}
