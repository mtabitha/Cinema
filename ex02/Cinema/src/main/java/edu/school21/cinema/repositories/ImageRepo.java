package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
