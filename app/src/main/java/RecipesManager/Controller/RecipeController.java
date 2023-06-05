package RecipesManager.Controller;
import RecipesManager.DTO.RecipeDTO;
import RecipesManager.Entity.Recipe;
import RecipesManager.Mapper.RecipeMapper;
import RecipesManager.Service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private RecipeMapper recipeMapper;

	@GetMapping
	public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
		List<RecipeDTO> recipes = recipeService.getAllRecipes();
		return new ResponseEntity<>(recipes, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
		RecipeDTO recipe = recipeService.getRecipeById(id);
		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDto) {
		RecipeDTO recipe = recipeService.createRecipe(recipeDto);
		return new ResponseEntity<>(recipe, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDto) {
		RecipeDTO recipe = recipeService.updateRecipe(id, recipeDto);
		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
		recipeService.deleteRecipe(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<RecipeDTO>> filterAllRecipes(
			@RequestParam(required = false) Boolean isVegetarian,
			@RequestParam(required = false) Integer servings,
			@RequestParam(required = false) String ingredient,
			@RequestParam(required = false) String withoutIngredient,
			@RequestParam(required = false) String instructionText) {
		List<RecipeDTO> recipes = recipeService.filterAllRecipes(isVegetarian, servings, ingredient, withoutIngredient, instructionText);
		return ResponseEntity.ok(recipes);
	}

}
