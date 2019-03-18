package hu.elte.CraftingGame.controllers;

import hu.elte.CraftingGame.entities.User;
import hu.elte.CraftingGame.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> post(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        return ResponseEntity.ok().build();
    }
    
}
