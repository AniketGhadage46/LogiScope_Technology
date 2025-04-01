package com.logiscope.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logiscope.users.exceptions.UserNotFoundException;
import com.logiscope.users.model.User;
import com.logiscope.users.repo.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // user create
    public User createUser(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Set current date
        calendar.add(Calendar.DAY_OF_YEAR, 30); // Add 30 days to the current date
        user.setPasswordExpiryDate(calendar.getTime()); // Set password expiry date

        return userRepository.save(user);
    }
    
    // Bulk create users
    public List<User> createUsers(List<User> users) {
        for (User user : users) {
            user.setPasswordExpiryDate(getPasswordExpiryDate()); //pass 30 day expiry
        }
        return userRepository.saveAll(users);
    }
    
    // Helper method to calculate password expiry date
    private Date getPasswordExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 30); // Set password expiry to 30 days from now
        return calendar.getTime();
    }
    

    // search by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    
    // Method to update a user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Method to delete a user by email
    public void deleteUserByEmail(String email) {
        userRepository.findByEmail(email).ifPresent(userRepository::delete);
    }
    
    //to check password is expired or not
    public boolean isPasswordExpired(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        User user = userOptional.get();
        Date expiryDate = user.getPasswordExpiryDate();
        return expiryDate.before(new Date());
    }

}
