package RecipesManager.Mapper;

import RecipesManager.DTO.IngredientDTO;
import RecipesManager.Entity.Ingredient;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

	default Long ingredientToId(Ingredient ingredient) {
		if (ingredient != null) {
			return ingredient.getId();
		} else {
			return null;
		}
	}

	default Ingredient idToIngredient(Long id) {
		if ( id == null ) {
			return null;
		}

		Ingredient ingredient = new Ingredient();
		ingredient.setId(id);
		return ingredient;
	}

	Set<Long> ingredientsToIds(Set<Ingredient> ingredients);

	Set<Ingredient> idsToIngredients(Set<Long> ids);
	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "name", target = "name")
	})
	IngredientDTO toDTO(Ingredient ingredient);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "name", target = "name")
	})
	Ingredient toEntity(IngredientDTO ingredientDTO);
}
