package hu.elte.CraftingGame.controllers;

import hu.elte.CraftingGame.entities.Element;
import hu.elte.CraftingGame.entities.User;
import hu.elte.CraftingGame.repositories.ElementRepository;
import hu.elte.CraftingGame.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ElementRepository elementRepository;
    
    @GetMapping("")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Iterable<User>> getAll() {
        Iterable<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/elements")
    @Secured({ "ROLE_ADMIN","ROLE_USER" })
    public ResponseEntity<Iterable<Element>> getAllElementIDs(@RequestHeader("authorization") String token) {
        Optional<User> optionalUser = userRepository.findByUsername(getUsername(token));
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Iterable<Element> elements = user.getElements();
            return ResponseEntity.ok(elements);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/insertelement")
    public ResponseEntity<User> postElement(@RequestHeader("authorization") String token, @RequestBody Element reqElement) {
        Optional<User> optionalUser = userRepository.findByUsername(getUsername(token));
        Optional<Element> optionalElement = elementRepository.findByElementName(reqElement.getElementName());
        if (!optionalUser.isPresent() || !optionalElement.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user = optionalUser.get();
        Element element = optionalElement.get();
        user.getElements().add(element);
        element.getUsers().add(user);

        elementRepository.save(element);
        return ResponseEntity.ok(userRepository.save(user));
    }

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

    private String getUsername(String token) {
        String base64Credentials = token.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);

        final String[] credentials = new String(credDecoded, StandardCharsets.UTF_8).split(":", 2);

        return credentials[0];
    }
    
}
