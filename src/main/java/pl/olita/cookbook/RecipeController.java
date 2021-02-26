package pl.olita.cookbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

    private RecipeService recipeService;
    private List<Recipe> recipesList;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        recipesList = recipeService.find3MostLikes();
        model.addAttribute("recipesList", recipesList);
        return "home";
    }

    @GetMapping("/recipe")
    public String recipesList(Model model, @RequestParam(required = false) Category category) {
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

    @GetMapping("/recipe/listIngredients")
    public String showIngredients(Model model) {
        List<Ingredient> ingredients = recipeService.findAllIngredients();
        model.addAttribute("ingredients", ingredients);
        return "listIngredients";
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

    @PostMapping("/addIngredients")
    public String addIngredient(Ingredient ingredient) {
        recipeService.saveIngredient(ingredient);
        return "redirect:/recipe/" + ingredient.getRecipe().getId();
    }

    @GetMapping("/recipe/{id}/edit")
    public String showRecipeEditForm(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeService.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipeToEdit", recipe);
            model.addAttribute("ingredients", recipeService.findAllIngredients());
            return "recipeEdit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/recipe/{id}/edit")
    public String editRecipe(@PathVariable Long id, Recipe recipe) {
        Recipe recipeToEdit = recipeService.findById(id).orElseThrow();

        recipeToEdit.setTitle(recipe.getTitle());
        recipeToEdit.setIntroduction(recipe.getIntroduction());
        recipeToEdit.setPrepTime(recipe.getPrepTime());
        recipeToEdit.setNumberOfServings(recipe.getNumberOfServings());
        recipeToEdit.setCategory(recipe.getCategory());
        recipeToEdit.setDescription(recipe.getDescription());
        recipeService.saveRecipe(recipeToEdit);
        return "redirect:/recipe/" + recipeToEdit.getId();
    }

    @GetMapping("/recipe/{id}/delete")
    public String delete(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return "redirect:/";
    }
}
