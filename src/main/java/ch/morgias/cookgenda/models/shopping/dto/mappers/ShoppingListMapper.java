package ch.morgias.cookgenda.models.shopping.dto.mappers;

import ch.morgias.cookgenda.models.shopping.ShoppingList;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ShoppingListFoodMapper.class})
public interface ShoppingListMapper {
    ShoppingListMapper INSTANCE = Mappers.getMapper(ShoppingListMapper.class);
    ShoppingListDto toShoppingListDto(ShoppingList shoppingList);
}
