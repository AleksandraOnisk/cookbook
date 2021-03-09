package pl.olita.cookbook.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error,
                            Model model) {
        boolean showErrorMessage = false;
        if (error != null) {
            showErrorMessage = true;
        }
        model.addAttribute("showErrorMessage", showErrorMessage);
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User user,
                          BindingResult bindResult) {
        if (bindResult.hasErrors())
            return "register";
        else {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();
            String rawPassword = user.getPassword();
            userService.registerUser(firstName, lastName, email, rawPassword);
            return "registerSuccess";
        }
    }

    @GetMapping("/reset")
    public String resetForm() {
        return "resetForm";
    }

    @PostMapping("/reset")
    public String resetPasswordLinkSend(@RequestParam String email) {
        userService.sendPasswordResetLink(email);
        return "resetFormSend";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(@RequestParam("klucz") String key, Model model) {
        model.addAttribute("key", key);
        return "resetFormWithKey";
    }

    @PostMapping("/resetEnding")
    public String resetPasswordLinkSend(@RequestParam String key, @RequestParam String password) {
        userService.updateUserPassword(key, password);
        return "redirect:/login";
    }

    @GetMapping("/user/{id}")
    public String showUserPanelForm(@PathVariable Long id, Model model) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("userToEdit", userOptional.get());
            return "userPanel";
        } else {
            return "error";
        }
    }

    @PostMapping("/user/{id}")
    public String editUser(@PathVariable Long id, User user) {
        userService.saveEditedUser(user, id);
        return "editUserSuccess";
    }
}
