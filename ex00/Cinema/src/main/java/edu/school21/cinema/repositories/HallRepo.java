package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Hall;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class HallRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Hall> findAll() {
        return entityManager.createQuery("select h from  Hall h", Hall.class).getResultList();
    }

    public Optional<Hall> findById(Long id) {
        return entityManager.createQuery("select h from Hall h where h.id=:id", Hall.class).setParameter("id", id).getResultStream().findAny();
    }

    @Transactional
    public void save(Hall entity) {
        entityManager.persist(entity);
    }
}
