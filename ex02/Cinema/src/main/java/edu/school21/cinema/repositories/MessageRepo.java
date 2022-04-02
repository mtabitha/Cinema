package edu.school21.cinema.repositories;

import edu.school21.cinema.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {


}
