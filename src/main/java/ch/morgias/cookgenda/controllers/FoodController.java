package ch.morgias.cookgenda.controllers;

import ch.morgias.cookgenda.models.food.dto.FoodNameDto;
import ch.morgias.cookgenda.models.food.dto.mappers.FoodMapper;
import ch.morgias.cookgenda.services.food.FoodService;
import infrastructure.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class FoodController {
    private FoodService foodService;

    @GetMapping(Routes.FOOD_INDEX)
    public Collection<FoodNameDto> getFoodNames() {
        return FoodMapper.INSTANCE.toFoodNameDtoList(foodService.getAllFoods());
    }
}
