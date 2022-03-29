package edu.school21.cinema.controller;

import edu.school21.cinema.model.AgeRestriction;
import edu.school21.cinema.model.Movie;
import edu.school21.cinema.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@RequestMapping("/admin/panel/films")
public class MovieController {

    @Autowired
    private MoveService moveService;

    @GetMapping
    public String getMovies(Model model) {
        model.addAttribute("movies", moveService.getMoves());
        model.addAttribute("ages", Arrays.asList(AgeRestriction.values()));
        return "Movie";
    }

    @PostMapping
    public String newMovie(@RequestParam String title,
                           @RequestParam String year,
                           @RequestParam String description,
                           @RequestParam AgeRestriction ageRestriction,
                           @RequestParam("file") MultipartFile file
    ) throws IOException {

        Movie movie = new Movie(title, year, description, ageRestriction);
        if (movie.isValid())
            moveService.addNew(movie, file);
        return "redirect:/admin/panel/films";
    }


}
