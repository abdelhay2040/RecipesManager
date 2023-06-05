package RecipesManager.DTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class RecipeDTO {
	private String title;
	private String instructions;
	private int servings;
	private boolean isVegetarian;
	private Set<Long> ingredientIds;

}