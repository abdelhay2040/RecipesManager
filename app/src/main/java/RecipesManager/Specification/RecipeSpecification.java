package RecipesManager.Specification;

import RecipesManager.Entity.Ingredient;
import RecipesManager.Entity.Recipe;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

public class RecipeSpecification {

	public static Specification<Recipe> isVegetarian(boolean isVegetarian) {
		return (recipe, cq, cb) -> cb.equal(recipe.get("isVegetarian"), isVegetarian);
	}

	public static Specification<Recipe> hasServings(int servings) {
		return (recipe, cq, cb) -> cb.equal(recipe.get("servings"), servings);
	}

	public static Specification<Recipe> hasIngredient(String ingredient) {
		return (recipe, cq, cb) -> {
			Join<Recipe, Ingredient> ingredientJoin = recipe.join("ingredients");
			return cb.like(cb.lower(ingredientJoin.get("name")), "%" + ingredient.toLowerCase() + "%");
		};
	}

	public static Specification<Recipe> doesNotHaveIngredient(String ingredient) {
		return (recipe, cq, cb) -> {
			Join<Recipe, Ingredient> ingredientJoin = recipe.join("ingredients");
			return cb.notLike(cb.lower(ingredientJoin.get("name")), "%" + ingredient.toLowerCase() + "%");
		};
	}

	public static Specification<Recipe> instructionContains(String text) {
		return (recipe, cq, cb) -> cb.like(recipe.get("instructions"), "%" + text + "%");
	}
}
