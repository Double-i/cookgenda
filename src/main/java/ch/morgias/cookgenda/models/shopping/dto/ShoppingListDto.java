package ch.morgias.cookgenda.models.shopping.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class ShoppingListDto {
    private Collection<ShoppingListFoodDto> shoppingListFoods;
}
