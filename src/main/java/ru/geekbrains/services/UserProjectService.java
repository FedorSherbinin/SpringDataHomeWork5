package ru.geekbrains.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Project;
import ru.geekbrains.entities.User;
import ru.geekbrains.entities.UsersProject;
import ru.geekbrains.repositories.ProjectRepository;
import ru.geekbrains.repositories.UserProjectRepository;
import ru.geekbrains.repositories.UserRepository;

import java.util.List;

@Service
public class UserProjectService {

    @Autowired
    private UserProjectRepository usersProjectRepository;

    private final UserRepository userRepository;
    private final UserService userService; // Внедряем UserService

    @Autowired
    public UserProjectService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService; // Инициализируем UserService
    }


    @Autowired
    private ProjectRepository projectRepository;

    public void addUserToProject(Long userId, Long projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Проект не найден"));

        UsersProject usersProject = new UsersProject();
        usersProject.setUser(user);
        usersProject.setProject(project);

        usersProjectRepository.save(usersProject);
    }

    // Получение пользователей по ID проекта
    public List<User> getUsersByProjectId(Long projectId) {
        return usersProjectRepository.findUsersByProjectId(projectId); // Исправлено имя переменной
    }

    // Получение проектов по ID пользователя
    public List<Project> getProjectsByUserId(Long userId) {
        return usersProjectRepository.findProjectsByUserId(userId); // Исправлено имя переменной
    }
}
