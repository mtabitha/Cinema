package edu.school21.cinema.service;

import edu.school21.cinema.model.Movie;
import edu.school21.cinema.repositories.MoveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class MoveService {

    @Autowired
    public MoveRepo moveRepo;

    @Value("${storage.path}")
    private String storagePath;

    public List<Movie> getMoves() {
        return moveRepo.findAll();
    }

    public void addNew(Movie movie, MultipartFile file) throws IOException {
        movie.setPosterLink(savePoster(file));
        moveRepo.save(movie);
    }

    private String savePoster(MultipartFile file) throws IOException {
        String resultFilename = "";
        if (file != null) {
            File storageDir = new File(storagePath);
            if (!storageDir.exists())
                storageDir.mkdir();
            resultFilename = UUID.randomUUID() + "." + file.getOriginalFilename();
            file.transferTo(new File(storagePath + "/" + resultFilename));
        }
        return resultFilename;
    }

}
