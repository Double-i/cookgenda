package ch.morgias.cookgenda.services.food;

import ch.morgias.cookgenda.models.food.Food;
import ch.morgias.cookgenda.models.food.dto.FoodEditDto;

import java.util.Collection;

public interface FoodService {
    Collection<Food> getAllFoods();
    void createFood(FoodEditDto foodEditDto);
    Food findFoodById(Long id);
    void deleteById(Long id);
}
