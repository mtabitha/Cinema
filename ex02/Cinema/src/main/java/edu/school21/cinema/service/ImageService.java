package edu.school21.cinema.service;

import edu.school21.cinema.model.Image;
import edu.school21.cinema.repositories.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    @Value("${storage.path}")
    private String storagePath;

    private void createDir(String path) {
        File storageDir = new File(storagePath + path);
        if (!storageDir.exists())
            storageDir.mkdir();

    }

    public void saveToDisk(Image image, MultipartFile file) {
        createDir("");
        createDir(image.getPath());
        String resultFilename = image.getUuid();
        try {
            file.transferTo(new File( storagePath + image.getPath() + "/" + resultFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image addNew(String path, MultipartFile file) {
        Image image = new Image(
                UUID.randomUUID() + file.getOriginalFilename(),
                file.getOriginalFilename(),
                path
        );
        image = imageRepo.save(image);
        saveToDisk(image, file);
        return image;
    }
}
