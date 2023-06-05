package RecipesManager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RecipesManager.DTO.IngredientDTO;
import RecipesManager.Entity.Ingredient;
import RecipesManager.Mapper.IngredientMapper;
import RecipesManager.Repository.IngredientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

	private final IngredientRepository ingredientRepository;
	private final IngredientMapper ingredientMapper;

	@Autowired
	public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
		this.ingredientRepository = ingredientRepository;
		this.ingredientMapper = ingredientMapper;
	}

	public IngredientDTO createIngredient(IngredientDTO ingredientDTO) {
		Ingredient ingredient = ingredientMapper.toEntity(ingredientDTO);
		ingredient = ingredientRepository.save(ingredient);
		return ingredientMapper.toDTO(ingredient);
	}

	public List<IngredientDTO> getAllIngredients() {
		return ingredientRepository.findAll()
				.stream()
				.map(ingredientMapper::toDTO)
				.collect(Collectors.toList());
	}

	public IngredientDTO getIngredientById(Long id) {
		Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient not found"));
		return ingredientMapper.toDTO(ingredient);
	}

	public void deleteIngredient(Long id) {
		ingredientRepository.deleteById(id);
	}

	public IngredientDTO updateIngredient(Long id, IngredientDTO ingredientDTO) {
		Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient not found"));
		ingredient.setName(ingredientDTO.getName());
		ingredient = ingredientRepository.save(ingredient);
		return ingredientMapper.toDTO(ingredient);
	}
}
