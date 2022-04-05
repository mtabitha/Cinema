package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Hall;
import edu.school21.cinema.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ImageRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Image save(Image entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

}
