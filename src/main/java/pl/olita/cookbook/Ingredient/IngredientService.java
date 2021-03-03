package pl.olita.cookbook.Ingredient;

import org.springframework.stereotype.Service;
import pl.olita.cookbook.Recipe.RecipeRepository;

import java.util.List;

@Service
public class IngredientService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public IngredientService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
}

