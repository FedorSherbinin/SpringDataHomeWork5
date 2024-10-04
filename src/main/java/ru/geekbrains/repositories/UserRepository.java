package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
