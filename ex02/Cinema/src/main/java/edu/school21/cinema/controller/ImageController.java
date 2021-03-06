package edu.school21.cinema.controller;

import edu.school21.cinema.model.User;
import edu.school21.cinema.repositories.UserRepo;
import edu.school21.cinema.service.ImageService;
import edu.school21.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ImageController {

    @Autowired
    private UserService userService;


    @PostMapping("/films/{id}/chat/images")
    @ResponseBody
    public void newImage(@RequestParam("file") MultipartFile file) {

        userService.addUserAvatar(file);

    }

}
