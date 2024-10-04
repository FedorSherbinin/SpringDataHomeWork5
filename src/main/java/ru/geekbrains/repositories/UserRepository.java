package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
