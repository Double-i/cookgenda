package ch.morgias.cookgenda.models.shopping.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ShoppingListResumeDto {
    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer shoppingListSize;
    private Integer numberOfCheckedFoods;
}
