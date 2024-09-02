package ch.morgias.cookgenda.models.shopping.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GeneratingShoppingListDto {
    private LocalDate fromDate;
    private LocalDate toDate;
}
