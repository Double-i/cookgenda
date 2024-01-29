package ch.morgias.cookgenda.models.food.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddFoodToRecipeDto {
    private Long foodId;
    private Long quantity;
}
