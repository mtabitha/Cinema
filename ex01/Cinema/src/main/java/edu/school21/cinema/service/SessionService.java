package edu.school21.cinema.service;

import edu.school21.cinema.model.Session;
import edu.school21.cinema.repositories.HallRepo;
import edu.school21.cinema.repositories.MovieRepo;
import edu.school21.cinema.repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

        System.out.println(filmName);

        List<Session> sessions = null;
        if (filmName != null && !filmName.isEmpty()) {
            sessions = sessionRepo.findByMovie(movieRepo.findByTitle(filmName));
            sessions.forEach(System.out::println);
        }
        return sessions;

    }

    public Optional<Session> getSession(Long id) {
        return sessionRepo.findById(id);
    }
}
