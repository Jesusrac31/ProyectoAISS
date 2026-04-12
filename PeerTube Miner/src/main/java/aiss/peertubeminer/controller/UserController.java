package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.videominer.User;
import aiss.peertubeminer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

