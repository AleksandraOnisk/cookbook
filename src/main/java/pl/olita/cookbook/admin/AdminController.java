package pl.olita.cookbook.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.olita.cookbook.ingredient.Ingredient;
import pl.olita.cookbook.user.User;
import pl.olita.cookbook.user.UserService;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String adminPanel(Model model) {
        List<User> usersList = userService.findAllWithoutCurrentUser();
        model.addAttribute("usersList", usersList);
        return "admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
