package edu.school21.cinema.service;

import edu.school21.cinema.model.User;
import edu.school21.cinema.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ImageService imageService;

    @Autowired
    private HttpServletRequest req;

    public User getUserId(Long userId) {
        System.out.println("Hello form UserService");
        return userRepo.findById(userId).get();
    }

    public User addNew(User newUser) {
        return userRepo.save(newUser);
    }

    @Transactional
    public void addUserAvatar(MultipartFile file) {
        Long id = Long.parseLong(Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("id")).findAny().get().getValue());
        User userFromDB = userRepo.findById(id).get();
        userFromDB.setAvatar(imageService.addNew("/avatars", file));
        userRepo.save(userFromDB);
    }
}
