package edu.school21.cinema.service;

import edu.school21.cinema.model.Movie;
import edu.school21.cinema.model.Session;
import edu.school21.cinema.repositories.HallRepo;
import edu.school21.cinema.repositories.MovieRepo;
import edu.school21.cinema.repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SessionService {

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private HallRepo hallRepo;

    @Autowired
    private MovieRepo movieRepo;

    public List<Session> getSessions() {
        return sessionRepo.findAll();
    }

    public void addNew(Session session, Long hallId, Long movieId) {
        session.setHall(hallRepo.findById(hallId).get());
        session.setMovie(movieRepo.findById(movieId).get());
        sessionRepo.save(session);
    }

    public List<Session> showMovies(String filmName) {

        List<Session> sessions = new ArrayList<>();
        if (filmName != null && !filmName.isEmpty()) {
            for (Movie movie : movieRepo.findByTitleLike(filmName)) {
                sessionRepo.findByMovie(movie).ifPresent(sessions::addAll);
            }
        }
        return sessions;

    }

    public Optional<Session> getSession(Long id) {
        return sessionRepo.findById(id);
    }
}
