package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entities.UsersProject;

public interface UsersProjectRepository extends JpaRepository<UsersProject, Long> {
}
