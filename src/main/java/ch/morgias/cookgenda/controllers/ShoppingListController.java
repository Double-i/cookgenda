package ch.morgias.cookgenda.controllers;

import ch.morgias.cookgenda.infrastructure.Routes;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListDto;
import ch.morgias.cookgenda.models.shopping.dto.mappers.ShoppingListMapper;
import ch.morgias.cookgenda.services.shopping.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping(Routes.SHOPPING_LIST_GENERATE_FROM_TO)
    public ShoppingListDto generateShoppingList(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime from,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to) {
        return ShoppingListMapper.INSTANCE.toShoppingListDto(shoppingListService.generateShoppingList(from, to));
    }
}
