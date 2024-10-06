package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.entities.Project;
import ru.geekbrains.entities.User;
import ru.geekbrains.entities.UsersProject;

import java.util.List;

public interface UserProjectRepository extends JpaRepository<UsersProject, Long> {

    @Query("SELECT u FROM UsersProject up JOIN up.user u WHERE up.project.id = :projectId")
    List<User> findUsersByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT p FROM UsersProject up JOIN up.project p WHERE up.user.id = :userId")
    List<Project> findProjectsByUserId(@Param("userId") Long userId);
}
