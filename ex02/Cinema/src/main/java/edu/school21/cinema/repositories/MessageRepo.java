package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MessageRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Message save(Message message) {
        entityManager.persist(message);
        entityManager.flush();
        return message;
    }

    public List<Message> findTop20ByChannelIdOrderByDateTimeDesc(Long channelId) {
        return entityManager.createQuery("select m from Message m where m.channelId=:channelId order by m.dateTime desc", Message.class).setParameter("channelId", channelId).setMaxResults(20).getResultList();
    }
}
