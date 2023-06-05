package RecipesManager.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.*;
@Entity
@Getter
@Setter
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@ManyToMany(mappedBy = "ingredients")
	private Set<Recipe> recipes;


}