package edu.school21.cinema.repositories;

import edu.school21.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
