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

    private RecipeRepository recipeRepository;
    private List<Recipe> recipesList;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        recipesList = recipeRepository.findByFavouriteTrue();
        model.addAttribute("recipesList", recipesList);
        return "home";
    }

    @GetMapping("/recipe")
    public String recipesList(Model model, @RequestParam(required = false) Category category) {
        if (category != null) {
            recipesList = recipeRepository.findByCategoryOrderByTitleAsc(category);
        } else {
            recipesList = recipeRepository.findAll();
        }
        model.addAttribute("recipesList", recipesList);
        return "recipesList";
    }

    @GetMapping("/recipe/add")
    public String addNewRecipeForm(Model model) {
        Recipe newOne = new Recipe();
        model.addAttribute("recipe", newOne);
        return "recipeAdd";
    }

    @PostMapping("/recipe/add")
    public String addNewRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return "redirect:/recipe";
    }

    @GetMapping("/recipe/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipe", recipe);
            return "recipe";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/recipe/{id}/edit")
    public String showRecipeEditForm(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipeToEdit", recipe);
            return "recipeEdit";
        } else {
            return "redirect:/";
        }
    }

}
