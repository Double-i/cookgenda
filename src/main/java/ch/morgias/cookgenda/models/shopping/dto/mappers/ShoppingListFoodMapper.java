package ch.morgias.cookgenda.models.shopping.dto.mappers;

import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListFoodDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface ShoppingListFoodMapper {
    ShoppingListFoodMapper INSTANCE = Mappers.getMapper(ShoppingListFoodMapper.class);

    @Mapping(source = "food.name", target = "name")
    ShoppingListFoodDto toShoppingListDto(ShoppingListFood shoppingList);

    Collection<ShoppingListFoodDto> toShoppingListFoodListDto(Collection<ShoppingListFood> shoppingList);
}
