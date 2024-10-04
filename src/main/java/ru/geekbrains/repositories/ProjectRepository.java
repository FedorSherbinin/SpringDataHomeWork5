package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
