package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Movie;
import edu.school21.cinema.model.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class SessionRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Session> findAll() {
        return entityManager.createQuery("select s from  Session s", Session.class).getResultList();
    }


    public void save(Session entity) {
        entityManager.persist(entity);
    }

    public Optional<List<Session>> findByMovie(Movie movie) {
        return Optional.ofNullable(entityManager.createQuery("select s from Session s where s.movie=:movie", Session.class).setParameter("movie", movie).getResultList()) ;
    }

    public Optional<Session> findById(Long id) {
        return entityManager.createQuery("select s from Session s where s.id=:id", Session.class).setParameter("id", id).getResultStream().findAny();
    }
}

