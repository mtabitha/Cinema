package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {

    Movie findByTitle(String title);

}
