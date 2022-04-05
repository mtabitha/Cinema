package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class MovieRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Movie> findAll() {
        return entityManager.createQuery("select m from  Movie m", Movie.class).getResultList();
    }

    public Optional<Movie> findById(Long id) {
        return entityManager.createQuery("select m from Movie m where m.id=:id", Movie.class).setParameter("id", id).getResultStream().findAny();
    }

    @Transactional
    public void save(Movie entity) {
        entityManager.persist(entity);
    }

    public List<Movie> findByTitleLike(String filmName) {
        return  entityManager.createQuery("select m from Movie m where lower(m.title) like :filmName", Movie.class).setParameter("filmName", "%" + filmName.toLowerCase(Locale.ROOT) + "%").getResultList();
    }
}
