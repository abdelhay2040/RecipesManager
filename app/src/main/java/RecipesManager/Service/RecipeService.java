package RecipesManager.Service;

import RecipesManager.DTO.RecipeDTO;
import RecipesManager.Entity.Ingredient;
import RecipesManager.Entity.Recipe;
import RecipesManager.Exception.ResourceNotFoundException;
import RecipesManager.Mapper.RecipeMapper;
import RecipesManager.Repository.IngredientRepository;
import RecipesManager.Repository.RecipeRepository;
import RecipesManager.Specification.RecipeSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	private RecipeMapper recipeMapper;

	@Transactional
	public RecipeDTO createRecipe(RecipeDTO recipeDto) {
		Recipe recipe = recipeMapper.toEntity(recipeDto);

		Set<Ingredient> ingredients = ingredientRepository.findByIdIn(recipeDto.getIngredientIds());
		recipe.setIngredients(ingredients);

		Recipe savedRecipe = recipeRepository.save(recipe);
		return recipeMapper.toDTO(savedRecipe);
	}

	public List<RecipeDTO> getAllRecipes() {
		List<Recipe> recipes = recipeRepository.findAll();
		return recipeMapper.toDTOs(recipes);
	}

	public List<RecipeDTO> filterAllRecipes(Boolean isVegetarian, Integer servings, String ingredient, String withoutIngredient, String instructionText) {
		Specification<Recipe> spec = Specification.where(null);

		if (isVegetarian != null) {
			spec = spec.and(RecipeSpecification.isVegetarian(isVegetarian));
		}
		if (servings != null) {
			spec = spec.and(RecipeSpecification.hasServings(servings));
		}
		if (withoutIngredient != null) {
			spec = spec.and(RecipeSpecification.doesNotHaveIngredient(withoutIngredient));
		}

		if (ingredient != null) {
			spec = spec.and(RecipeSpecification.hasIngredient(ingredient));
		}
		if (instructionText != null) {
			spec = spec.and(RecipeSpecification.instructionContains(instructionText));
		}

		List<Recipe> recipes = recipeRepository.findAll(spec);
		return recipeMapper.toDTOs(recipes);
	}
	public RecipeDTO getRecipeById(Long id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		return recipe.map(recipeMapper::toDTO).orElse(null);
	}

	public RecipeDTO updateRecipe(Long id, RecipeDTO updatedRecipeDto) {
		Recipe existingRecipe = recipeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", id));

		// Map the updated recipe DTO to an entity
		Recipe updatedRecipe = recipeMapper.toEntity(updatedRecipeDto);

		// Set the existing recipe's fields to the updated values
		existingRecipe.setTitle(updatedRecipe.getTitle());
		existingRecipe.setInstructions(updatedRecipe.getInstructions());
		existingRecipe.setServings(updatedRecipe.getServings());
		existingRecipe.setVegetarian(updatedRecipe.isVegetarian());

		// Fetch the ingredients by their ids and set them to the existing recipe
		Set<Ingredient> ingredients = ingredientRepository.findByIdIn(updatedRecipeDto.getIngredientIds());
		existingRecipe.setIngredients(ingredients);

		// Save and return the updated recipe
		Recipe savedRecipe = recipeRepository.save(existingRecipe);
		return recipeMapper.toDTO(savedRecipe);
	}

	public void deleteRecipe(Long id) {
		if (!recipeRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recipe", "id", id);
		}

		recipeRepository.deleteById(id);
	}
}
