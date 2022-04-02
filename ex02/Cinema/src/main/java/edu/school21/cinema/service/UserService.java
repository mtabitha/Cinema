package edu.school21.cinema.service;

import edu.school21.cinema.model.User;
import edu.school21.cinema.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserId(Long userId) {
        System.out.println("Hello form UserService");
        return userRepo.findById(userId).get();
    }

    public User addNew(User newUser) {
        return userRepo.save(newUser);
    }
}
