package pl.olita.cookbook;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Recipe> findByFavouriteTrue() {
        return recipeRepository.findByFavouriteTrue();
    }

    public List<Recipe> findByCategoryOrderByTitleAsc(Category category) {
        return recipeRepository.findByCategoryOrderByTitleAsc(category);
    }

    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }
}
