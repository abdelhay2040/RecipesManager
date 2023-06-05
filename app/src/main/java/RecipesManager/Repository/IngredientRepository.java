package RecipesManager.Repository;

import RecipesManager.Entity.Ingredient;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	Set<Ingredient> findByIdIn(Set<Long> ids);

}