package pl.olita.cookbook.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.olita.cookbook.category.Category;
import pl.olita.cookbook.ingredient.Ingredient;
import pl.olita.cookbook.user.User;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Recipe> recipesList = recipeService.find3MostLikes();
        List<User> users = recipeService.findCurrentUser();
        model.addAttribute("recipesList", recipesList);
        model.addAttribute("users", users);
        return "home";
    }

    @GetMapping("/recipe")
    public String recipesList(Model model, @RequestParam(required = false) Category category) {
        List<Recipe> recipesList;
        if (category != null) {
            recipesList = recipeService.findByCategoryOrderByTitleAsc(category);
        } else {
            recipesList = recipeService.findAllRecipe();
        }
        model.addAttribute("recipesList", recipesList);
        return "recipesList";
    }

    @GetMapping("/recipe/add")
    public String addNewRecipeForm(Model model) {
        Recipe newRecipe = new Recipe();
        model.addAttribute("recipe", newRecipe);
        return "recipeAdd";
    }

    @PostMapping("/recipe/add")
    public String addNewRecipe(Recipe recipe) {
        recipeService.saveRecipe(recipe);
        return "redirect:/recipe";
    }

    @GetMapping("/recipe/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeService.findById(id);
        if (recipeOptional.isPresent()) {
            model.addAttribute("recipe", recipeOptional.get());
            return "recipe";
        } else {
            return "error";
        }
    }

    @GetMapping("/recipe/{id}/addLike")
    String addLike(@PathVariable Long id) {
        recipeService.addLike(id);
        return "redirect:/recipe/" + id;
    }

    @GetMapping("/addIngredients")
    public String addIngredientForm(Model model, @RequestParam(required = false, defaultValue = "1") Long Id) {
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipeService.findById(Id));
        if (recipeService.findById(Id).isPresent()) {
            model.addAttribute("ingredient", ingredient);
            model.addAttribute("recipes", recipeService.findAllRecipe());
            return "addIngredients";
        }
        return "error";

    }
    @GetMapping("/recipe/{id}/edit")
    public String showRecipeEditForm(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeService.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipeToEdit", recipe);
            model.addAttribute("ingredients", recipe.findAllIngredients());
            return "recipeEdit";
        } else {
            return "error";
        }
    }

    @PostMapping("/recipe/{id}/edit")
    public String editRecipe(@PathVariable Long id, Recipe recipe) {
        recipeService.saveEditedRecipe(recipe, id);
        return "redirect:/recipe/" + recipe.getId();
    }

    @GetMapping("/recipe/{id}/delete")
    public String delete(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return "redirect:/recipe/";
    }
}
