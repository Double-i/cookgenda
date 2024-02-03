package ch.morgias.cookgenda.repositories;

import ch.morgias.cookgenda.models.agenda.PlanedRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanedRecipeRepository extends JpaRepository<PlanedRecipe, Long> {
    @Query("select pr from PlanedRecipe pr " +
            "join fetch pr.recipe " +
            "where pr.planedDate between :to and :from ")
    List<PlanedRecipe> findByPeriod(LocalDateTime to, LocalDateTime from);
}
