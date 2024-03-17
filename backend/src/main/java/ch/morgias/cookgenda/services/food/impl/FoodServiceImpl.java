package ch.morgias.cookgenda.services.food.impl;

import ch.morgias.cookgenda.models.food.Food;
import ch.morgias.cookgenda.models.food.dto.FoodEditDto;
import ch.morgias.cookgenda.repositories.FoodRepository;
import ch.morgias.cookgenda.services.food.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;

    @Override
    public Collection<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public void createFood(FoodEditDto foodEditDto) {

    }

    @Override
    public Food findFoodById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
