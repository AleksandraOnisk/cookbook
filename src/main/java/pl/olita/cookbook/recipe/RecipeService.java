package pl.olita.cookbook.recipe;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.olita.cookbook.category.Category;
import pl.olita.cookbook.ingredient.IngredientRepository;
import pl.olita.cookbook.user.User;
import pl.olita.cookbook.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
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
        Recipe recipeToEdit = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recipe with id " + id + " not found"));
        recipeToEdit.setTitle(recipe.getTitle());
        recipeToEdit.setIntroduction(recipe.getIntroduction());
        recipeToEdit.setPrepTime(recipe.getPrepTime());
        recipeToEdit.setNumberOfServings(recipe.getNumberOfServings());
        recipeToEdit.setCategory(recipe.getCategory());
        recipeToEdit.setDescription(recipe.getDescription());
        return recipeRepository.save(recipeToEdit);
    }

    public List<User> findCurrentUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(currentUser.getName()))
                .collect(Collectors.toList());
    }
}
