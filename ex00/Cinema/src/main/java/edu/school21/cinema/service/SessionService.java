package edu.school21.cinema.service;

import edu.school21.cinema.model.Session;
import edu.school21.cinema.repositories.HallRepo;
import edu.school21.cinema.repositories.MovieRepo;
import edu.school21.cinema.repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    public void addNew(Session session, Long hallId, Long movieId) {
        session.setHall(hallRepo.findById(hallId).get());
        session.setMovie(movieRepo.findById(movieId).get());
        sessionRepo.save(session);
    }
}
