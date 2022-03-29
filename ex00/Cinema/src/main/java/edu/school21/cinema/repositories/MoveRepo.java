package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepo extends JpaRepository<Movie, Long> {
}
