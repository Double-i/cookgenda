package ch.morgias.cookgenda.controllers;

import ch.morgias.cookgenda.infrastructure.Routes;
import ch.morgias.cookgenda.models.shopping.dto.*;
import ch.morgias.cookgenda.models.shopping.dto.mappers.ShoppingListFoodMapper;
import ch.morgias.cookgenda.models.shopping.dto.mappers.ShoppingListMapper;
import ch.morgias.cookgenda.services.shopping.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @PostMapping(Routes.SHOPPING_LIST_GENERATE_FROM_TO)
    public ShoppingListResumeDto generateShoppingList(@RequestBody GeneratingShoppingListDto shoppingList) {
        return ShoppingListMapper.INSTANCE.toShoppingListResumeDto(shoppingListService.generateShoppingList(shoppingList));
    }


    @GetMapping(Routes.SHOPPING_LISTS_SPECIFIC)
    public ShoppingListDto getSpecificShoppingList(@PathVariable Long shoppingListId) {
        return ShoppingListMapper.INSTANCE.toShoppingListDto(shoppingListService.getShoppingListById(shoppingListId));
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
