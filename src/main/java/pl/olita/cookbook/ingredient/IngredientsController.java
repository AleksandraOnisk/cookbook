package pl.olita.cookbook.ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IngredientsController {

    private IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/listIngredients")
    public String showIngredients(Model model) {
        List<Ingredient> ingredients = ingredientService.findAllIngredients();
        model.addAttribute("ingredients", ingredients);
        return "listIngredients";
    }

    @PostMapping("/addIngredients")
    public String addIngredient(Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return "redirect:/recipe/" + ingredient.getRecipe().getId();
    }
}
