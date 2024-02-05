package com.budgettracker.api.Services;

import com.budgettracker.api.Entities.User;
import com.budgettracker.api.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            // Diğer alanları da güncelle...

            return userRepository.save(existingUser);
        } else {
            return null; // Belirtilen ID ile kullanıcı bulunamadı
        }
    }

    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false; // Belirtilen ID ile kullanıcı bulunamadı
        }
    }
}
