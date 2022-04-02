package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    List<Message> findAllByChannelId(Long channelId);


}
