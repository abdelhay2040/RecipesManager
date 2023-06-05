package RecipesManager.Service;

import RecipesManager.DTO.RecipeDTO;
import RecipesManager.Entity.Ingredient;
import RecipesManager.Entity.Recipe;
import RecipesManager.Exception.ResourceNotFoundException;
import RecipesManager.Mapper.RecipeMapper;
import RecipesManager.Repository.IngredientRepository;
import RecipesManager.Repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

	@InjectMocks
	private RecipeService recipeService;

	@Mock
	private RecipeRepository recipeRepository;

	@Mock
	private IngredientRepository ingredientRepository;

	@Mock
	private RecipeMapper recipeMapper;

	private Recipe testRecipe;
	private RecipeDTO testRecipeDto;

	@Before
	public void setup() {
		testRecipe = new Recipe();
		testRecipe.setTitle("Test Recipe");

		testRecipeDto = new RecipeDTO();
		testRecipeDto.setTitle("Test Recipe DTO");

		when(recipeMapper.toDTO(any(Recipe.class))).thenReturn(testRecipeDto);
		when(recipeMapper.toEntity(any(RecipeDTO.class))).thenReturn(testRecipe);
	}

	@Test
	public void testCreateRecipe() {
		when(recipeRepository.save(any(Recipe.class))).thenReturn(testRecipe);
		when(ingredientRepository.findByIdIn(any())).thenReturn(new HashSet<Ingredient>());

		RecipeDTO resultDto = recipeService.createRecipe(testRecipeDto);

		assertNotNull(resultDto);
		assertEquals(testRecipeDto.getTitle(), resultDto.getTitle());
	}

	@Test
	public void testGetRecipeById() {
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(testRecipe));

		RecipeDTO resultDto = recipeService.getRecipeById(1L);

		assertNotNull(resultDto);
		assertEquals(testRecipeDto.getTitle(), resultDto.getTitle());
	}

	@Test
	public void testGetAllRecipes() {
		List<Recipe> recipeList = new ArrayList<>();
		recipeList.add(testRecipe);

		when(recipeRepository.findAll()).thenReturn(recipeList);

		List<RecipeDTO> resultDtoList = recipeService.getAllRecipes();

		assertNotNull(resultDtoList);
		assertEquals(1, resultDtoList.size());
		assertEquals(testRecipeDto.getTitle(), resultDtoList.get(0).getTitle());
	}

	// similarly write tests for updateRecipe, deleteRecipe, and filterAllRecipes
}
