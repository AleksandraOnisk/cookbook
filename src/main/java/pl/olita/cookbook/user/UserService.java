package pl.olita.cookbook.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.olita.cookbook.mail.MailSenderService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final UserRoleRepository userRoleRepository;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       MailSenderService mailSenderService, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.mailSenderService = mailSenderService;
        this.userRoleRepository = userRoleRepository;
    }


    public void registerUser(String firstName, String lastName, String username, String rawPassword) {
        User userToAdd = new User();
        userToAdd.setFirstName(firstName);
        userToAdd.setLastName(lastName);
        userToAdd.setEmail(username);
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        userToAdd.setPassword(encryptedPassword);
        List<UserRole> list = Collections.singletonList(new UserRole(userToAdd, Role.ROLE_USER));
        userToAdd.setRoles(new HashSet<>(list));
        userRepository.save(userToAdd);

    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllWithoutCurrentUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getEmail().equals(currentUser.getName()))
                .collect(Collectors.toList());
    }

    public void sendPasswordResetLink(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            String key = UUID.randomUUID().toString();
            user.setPasswordResetKey(key);
            userRepository.save(user);

            try {
                mailSenderService.sendPasswordResetLink(email, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void updateUserPassword(String key, String password) {
        userRepository.findByPasswordResetKey(key).ifPresent(
                user -> {
                    user.setPassword(passwordEncoder.encode(password));
                    user.setPasswordResetKey(null);
                    userRepository.save(user);
                }
        );
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User saveEditedUser(Long id, User user) {
        User userToEdit = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Użytkownik o id " + id + " nie znaleziony"));
        userToEdit.setFirstName(user.getFirstName());
        userToEdit.setLastName(user.getLastName());
        userToEdit.setPassword("{noop}" + user.getPassword());
        return userRepository.save(userToEdit);
    }
}
