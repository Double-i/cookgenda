package ch.morgias.cookgenda.models.food.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeDetailDto {
    private Long id;
    private String name;
    private String description;
    private String image;
    private List<RecipeFoodDto> recipeFoods;
}
