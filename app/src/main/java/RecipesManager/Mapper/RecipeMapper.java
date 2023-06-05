package RecipesManager.Mapper;

import RecipesManager.DTO.RecipeDTO;
import RecipesManager.Entity.Recipe;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public interface RecipeMapper {

	@Mapping(target = "ingredientIds", source = "ingredients")
	RecipeDTO toDTO(Recipe recipe);

	@IterableMapping(elementTargetType = RecipeDTO.class)
	List<RecipeDTO> toDTOs(List<Recipe> recipes);

	@Mapping(target = "ingredients", source = "ingredientIds")
	Recipe toEntity(RecipeDTO recipeDto);
}
