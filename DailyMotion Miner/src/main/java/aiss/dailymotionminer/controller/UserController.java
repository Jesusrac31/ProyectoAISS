package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.model.videominer.User;
import aiss.dailymotionminer.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // The path where you can access to this part of the API
public class UserController {
    private final UserRepository userRepository; // Storage of the info obtained

    // Constructor of the controller, from one repository, it creates the controller in order to make possible the communication
    @Autowired
    public UserController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // For some GET operation
    // If success, return 200 by default
    @GetMapping
    public List<User> findAll(){
        return userRepository.findAll();
    }

    // For some GET operation with some ID as a path variable
    // If success, return 200 by default
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return userRepository.findOneById(id);
    }

    // For some POST operation
    // If success, return 201 status
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@Valid @RequestBody User user) { return userRepository.create(user); }

    // For some PUT operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody User user, @PathVariable Long id) { userRepository.update(user, id); }

    // For some Delete operation with some ID as a path variable
    // If success, return 204 status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { userRepository.delete(id); }
}

