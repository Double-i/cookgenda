package ch.morgias.cookgenda.models.shopping.dto.mappers;

import ch.morgias.cookgenda.models.shopping.ShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.EditShoppingListFood;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListFoodDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface ShoppingListFoodMapper {
    ShoppingListFoodMapper INSTANCE = Mappers.getMapper(ShoppingListFoodMapper.class);

    @Mapping(source = "food.name", target = "name")
    @Mapping(source = "food.id", target = "foodId")
    ShoppingListFoodDto toShoppingListDto(ShoppingListFood shoppingList);

    Collection<ShoppingListFoodDto> toShoppingListFoodListDto(Collection<ShoppingListFood> shoppingList);

    // this method is used for patch, so we have to ignore null.
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateShoppingListFood(@MappingTarget ShoppingListFood food, EditShoppingListFood editShoppingListFood);
}
