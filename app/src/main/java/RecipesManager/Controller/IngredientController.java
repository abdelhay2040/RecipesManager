package RecipesManager.Controller;


import RecipesManager.DTO.IngredientDTO;
import RecipesManager.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	private final IngredientService ingredientService;

	@Autowired
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@PostMapping
	public ResponseEntity<IngredientDTO> createIngredient(@RequestBody IngredientDTO ingredientDto) {
		return ResponseEntity.ok(ingredientService.createIngredient(ingredientDto));
	}

	@GetMapping
	public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
		return ResponseEntity.ok(ingredientService.getAllIngredients());
	}

	@GetMapping("/{id}")
	public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable Long id) {
		return ResponseEntity.ok(ingredientService.getIngredientById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<IngredientDTO> updateIngredient(@PathVariable Long id, @RequestBody IngredientDTO ingredientDto) {
		return ResponseEntity.ok(ingredientService.updateIngredient(id, ingredientDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
		ingredientService.deleteIngredient(id);
		return ResponseEntity.ok().build();
	}
}
