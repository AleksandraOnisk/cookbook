package pl.olita.cookbook.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String rawPassword) {
        User userToAdd = new User();
        userToAdd.setUsername(username);
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        userToAdd.setPassword(encryptedPassword);
        List<UserRole> list = Collections.singletonList(new UserRole(userToAdd, Role.ROLE_USER));
        userToAdd.setRoles(new HashSet<>(list));
        userRepository.save(userToAdd);

    }
}
