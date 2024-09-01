package ch.morgias.cookgenda.models.shopping.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class ShoppingListDto {
    private Long id;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Collection<ShoppingListFoodDto> shoppingListFoods;
}
