package ch.morgias.cookgenda.models.food.dto.mappers;

import ch.morgias.cookgenda.models.food.Food;
import ch.morgias.cookgenda.models.food.dto.FoodNameDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface FoodMapper {
    FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);

    FoodNameDto toFoodNameDto(Food allFoods);

    Collection<FoodNameDto> toFoodNameDtoList(Collection<Food> allFoods);
}
