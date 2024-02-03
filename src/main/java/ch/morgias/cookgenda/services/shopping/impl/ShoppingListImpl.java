package ch.morgias.cookgenda.services.shopping.impl;

import ch.morgias.cookgenda.models.agenda.PlanedRecipe;
import ch.morgias.cookgenda.models.food.RecipeFood;
import ch.morgias.cookgenda.models.shopping.ShoppingList;
import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import ch.morgias.cookgenda.repositories.ShoppingListItemRepository;
import ch.morgias.cookgenda.repositories.ShoppingListRepository;
import ch.morgias.cookgenda.services.agenda.PlanedRecipeService;
import ch.morgias.cookgenda.services.food.RecipeService;
import ch.morgias.cookgenda.services.shopping.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShoppingListImpl implements ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;
    private final PlanedRecipeService planedRecipeService;
    private final RecipeService recipeService;

    @Override
    public ShoppingList generateShoppingList(LocalDateTime from, LocalDateTime to) {
        Collection<PlanedRecipe> planedRecipes = planedRecipeService.findPlanedRecipeByPeriodWithFoods(from, to);
        Collection<RecipeFood> recipeFoods = planedRecipes.stream()
                .flatMap(r -> r.getRecipe()
                        .getRecipeFoods()
                        .stream()
                ).toList();
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setFromDate(from);
        shoppingList.setToDate(to);
        // Is a Map because it allows us to have only one row for each food
        Map<Long, ShoppingListFood> shoppingListItem = transformRecipeFoodToShoppingListFood(recipeFoods, shoppingList);
        shoppingList.getShoppingListFoods().addAll(shoppingListItem.values());
        return shoppingListRepository.save(shoppingList);
    }

    private static Map<Long, ShoppingListFood> transformRecipeFoodToShoppingListFood(Collection<RecipeFood> recipeFoods, ShoppingList shoppingList) {
        return recipeFoods.stream()
                .reduce(new HashMap<Long, ShoppingListFood>(),
                        // Acc method
                        (acc, recipeFood) -> {
                            Long foodId = recipeFood.getFood().getId();
                            ShoppingListFood f = acc.get(foodId);
                            if (f == null) {
                                f = new ShoppingListFood();
                                f.setQuantity(recipeFood.getQuantity());
                                f.setFood(recipeFood.getFood());
                                f.setShoppingList(shoppingList);
                            } else {
                                f.setQuantity(f.getQuantity() + recipeFood.getQuantity());
                            }
                            acc.put(foodId, f);
                            return acc;
                        },
                        // combiner map
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;
                        }
                );
    }

    @Override
    public ShoppingList getShoppingListById(Long shoppingListId) {
        return null;
    }

    @Override
    public void checkShoppingItem(Long shoppingItemId) {

    }
}
