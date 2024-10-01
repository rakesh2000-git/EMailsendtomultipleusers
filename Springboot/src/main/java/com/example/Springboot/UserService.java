package com.example.Springboot;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or Update User
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    // Retrieve All Users
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a User by ID
    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Delete a User
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
