package edu.school21.cinema.service;

import edu.school21.cinema.model.Image;
import edu.school21.cinema.model.Movie;
import edu.school21.cinema.repositories.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    public MovieRepo movieRepo;

    @Autowired
    private ImageService imageService;

    public List<Movie> getMoves() {
        return movieRepo.findAll();
    }

    public void addNew(Movie movie, MultipartFile file) throws IOException {

        Image poster = null;
        if (file != null) {
            poster = imageService.addNew("/posters", file);
        }
        movie.setPoster(poster);
        movieRepo.save(movie);
    }


}
