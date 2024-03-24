package ch.morgias.cookgenda.models.food.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDetailDto {
    private Long id;
    private String name;
    private String description;
    private String image;
}
