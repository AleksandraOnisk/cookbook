package pl.olita.cookbook.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String register(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String username = user.getEmail();
        String rawPassword = user.getPassword();
        userService.registerUser(firstName, lastName, username, rawPassword);
        return "redirect:/";
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


}
