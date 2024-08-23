package ch.morgias.cookgenda.models.food;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<RecipeFood> recipeFoods = new HashSet<>();
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
}
