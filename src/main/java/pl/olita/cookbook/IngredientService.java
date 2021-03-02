package pl.olita.cookbook;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public IngredientService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Recipe> findByCategoryOrderByTitleAsc(Category category) {
        return recipeRepository.findByCategoryOrderByTitleAsc(category);
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
}

