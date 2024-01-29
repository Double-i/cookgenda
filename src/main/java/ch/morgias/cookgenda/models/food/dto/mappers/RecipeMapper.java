package ch.morgias.cookgenda.models.food.dto.mappers;

import ch.morgias.cookgenda.models.food.Recipe;
import ch.morgias.cookgenda.models.food.dto.RecipeEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    Recipe toEntity(RecipeEditDto recipeDto);
}
