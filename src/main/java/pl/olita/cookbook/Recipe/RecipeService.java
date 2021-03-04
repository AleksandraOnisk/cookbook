package pl.olita.cookbook.Recipe;

import org.springframework.stereotype.Service;
import pl.olita.cookbook.Category.Category;
import pl.olita.cookbook.Ingredient.IngredientRepository;

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

    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> find3MostLikes() {
        return recipeRepository.findTop3ByOrderByLikesDesc();
    }

    public void addLike(Long id) {
        recipeRepository.addLike(id);
    }

    public Recipe saveEditedRecipe(Recipe recipe, Long id) {
        Recipe recipeToEdit = recipeRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Recipe with id " + id + " not found"));
        recipeToEdit.setTitle(recipe.getTitle());
        recipeToEdit.setIntroduction(recipe.getIntroduction());
        recipeToEdit.setPrepTime(recipe.getPrepTime());
        recipeToEdit.setNumberOfServings(recipe.getNumberOfServings());
        recipeToEdit.setCategory(recipe.getCategory());
        recipeToEdit.setDescription(recipe.getDescription());
        return recipeRepository.save(recipeToEdit);
    }
}
