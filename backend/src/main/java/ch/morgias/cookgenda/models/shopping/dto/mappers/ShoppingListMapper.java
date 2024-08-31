package ch.morgias.cookgenda.models.shopping.dto.mappers;

import ch.morgias.cookgenda.models.shopping.ShoppingList;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListDto;
import ch.morgias.cookgenda.models.shopping.dto.ShoppingListResumeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ShoppingListFoodMapper.class})
public interface ShoppingListMapper {
    ShoppingListMapper INSTANCE = Mappers.getMapper(ShoppingListMapper.class);
    ShoppingListDto toShoppingListDto(ShoppingList shoppingList);

    ShoppingListResumeDto toShoppingListResumeDto(ShoppingList shoppingList);

    List<ShoppingListResumeDto> toShoppingListResumesDto(List<ShoppingList> shoppingLists);
}
