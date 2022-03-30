package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Movie;
import edu.school21.cinema.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepo extends JpaRepository<Session, Long> {

    List<Session> findByMovie(Movie movie);

}
