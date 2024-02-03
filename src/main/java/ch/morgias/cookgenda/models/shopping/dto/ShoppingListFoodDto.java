package ch.morgias.cookgenda.models.shopping.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingListFoodDto {
    private String name;
    private Long foodId;
    private Double quantity;
    private Boolean checked;
}
