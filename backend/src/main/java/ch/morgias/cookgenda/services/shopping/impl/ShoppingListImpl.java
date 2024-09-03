package ch.morgias.cookgenda.services.shopping.impl;

import ch.morgias.cookgenda.exceptions.ResourceNotFoundException;
import ch.morgias.cookgenda.models.agenda.PlanedRecipe;
import ch.morgias.cookgenda.models.food.RecipeFood;
import ch.morgias.cookgenda.models.shopping.ShoppingList;
import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.EditShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.GeneratingShoppingListDto;
import ch.morgias.cookgenda.models.shopping.dto.mappers.ShoppingListFoodMapper;
import ch.morgias.cookgenda.repositories.ShoppingListItemRepository;
import ch.morgias.cookgenda.repositories.ShoppingListRepository;
import ch.morgias.cookgenda.services.agenda.PlanedRecipeService;
import ch.morgias.cookgenda.services.food.RecipeService;
import ch.morgias.cookgenda.services.shopping.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShoppingListImpl implements ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;
    private final PlanedRecipeService planedRecipeService;
    private final RecipeService recipeService;

    @Override
    public ShoppingList generateShoppingList(GeneratingShoppingListDto shoppingListDto) {

        LocalDateTime from = shoppingListDto.getFromDate().atStartOfDay();
        LocalDateTime to = shoppingListDto.getToDate().plusDays(1).atStartOfDay().minusNanos(1); // toDate + 1 day - 1 nanosecond
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
                .reduce(new HashMap<>(),
                        (acc, recipeFood) -> {
                            Long foodId = recipeFood.getFood().getId();
                            ShoppingListFood f = acc.get(foodId);
                            if (f == null) {
                                f = new ShoppingListFood();
                                f.setQuantity(recipeFood.getQuantity());
                                f.setFood(recipeFood.getFood());
                                f.setShoppingList(shoppingList);
                                f.setPlanedDate(LocalDate.now()); // TODO pass planedRecipe to this method instead of recipeFoods to be able to get planedDate
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
        return shoppingListRepository.findByIdWithFoodItems(shoppingListId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ShoppingListFood getShoppingListFoodById(Long shoppingListFoodId) {
        return shoppingListItemRepository.findByIdWithFood(shoppingListFoodId).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ShoppingListFood updateShoppingListFoodById(Long shoppingListFoodId, EditShoppingListFood editShoppingListFood) {
        ShoppingListFood food = getShoppingListFoodById(shoppingListFoodId);
        ShoppingListFoodMapper.INSTANCE.updateShoppingListFood(food, editShoppingListFood);
        shoppingListItemRepository.save(food);
        return food;
    }

    @Override
    public void deleteShoppingListFoodById(Long shoppingListFoodId) {
        shoppingListItemRepository.delete(shoppingListItemRepository.getReferenceById(shoppingListFoodId));
    }

    @Override
    public List<ShoppingList> getShoppingLists() {
        return shoppingListRepository.findAllWithFoodList();
    }
}
