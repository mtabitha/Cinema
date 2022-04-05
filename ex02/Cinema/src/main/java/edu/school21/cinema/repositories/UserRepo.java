package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Hall;
import edu.school21.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class UserRepo  {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<User> findById(Long id) {
        return entityManager.createQuery("select u from User u where u.id=:id", User.class).setParameter("id", id).getResultStream().findAny();
    }


    public User save(User entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

}
