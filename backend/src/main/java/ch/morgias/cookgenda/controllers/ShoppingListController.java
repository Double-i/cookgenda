package ch.morgias.cookgenda.controllers;

import ch.morgias.cookgenda.infrastructure.Routes;
import ch.morgias.cookgenda.models.shopping.dto.EditShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListDto;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListFoodDto;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListResumeDto;
import ch.morgias.cookgenda.models.shopping.dto.mappers.ShoppingListFoodMapper;
import ch.morgias.cookgenda.models.shopping.dto.mappers.ShoppingListMapper;
import ch.morgias.cookgenda.services.shopping.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping(Routes.SHOPPING_LIST_GENERATE_FROM_TO)
    public ShoppingListDto generateShoppingList(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime from,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to) {
        return ShoppingListMapper.INSTANCE.toShoppingListDto(shoppingListService.generateShoppingList(from, to));
    }

    @GetMapping(Routes.SHOPPING_LISTS)
    public List<ShoppingListResumeDto> generateShoppingList() {
        return ShoppingListMapper.INSTANCE.toShoppingListResumesDto(shoppingListService.getShoppingLists());
    }

    @PatchMapping(Routes.SHOPPING_LIST_FOOD_UPDATE)
    public ShoppingListFoodDto updateShoppingListFood(@PathVariable Long shoppingListFoodId, @RequestParam EditShoppingListFood editShoppingListFood) {
        return ShoppingListFoodMapper.INSTANCE.toShoppingListDto(shoppingListService.updateShoppingListFoodById(shoppingListFoodId, editShoppingListFood));
    }

    @DeleteMapping(Routes.SHOPPING_LIST_FOOD_DELETE)
    public void deleteShoppingListFood(@PathVariable Long shoppingListFoodId) {
        shoppingListService.deleteShoppingListFoodById(shoppingListFoodId);
    }
}
