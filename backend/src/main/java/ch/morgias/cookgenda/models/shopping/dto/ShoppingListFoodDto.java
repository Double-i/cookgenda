package ch.morgias.cookgenda.models.shopping.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ShoppingListFoodDto {
    private Long id;
    private Long foodId;
    private String name;
    private Double quantity;
    private LocalDate planedDate;
    private Boolean checked;
}
