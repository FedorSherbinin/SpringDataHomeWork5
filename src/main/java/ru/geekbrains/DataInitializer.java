package ru.geekbrains;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.geekbrains.entities.Project;
import ru.geekbrains.entities.User;
import ru.geekbrains.repositories.ProjectRepository;
import ru.geekbrains.repositories.UserRepository;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public DataInitializer(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Проверяем, существует ли проект A
        Optional<Project> projectAOptional = projectRepository.findByName("Проект A");
        Project project1;
        if (projectAOptional.isEmpty()) {
            project1 = new Project();
            project1.setName("Проект A");
            projectRepository.save(project1); // Сохраняем проект A, если его нет
        } else {
            project1 = projectAOptional.get();
        }

        // Проверяем, существует ли проект B
        Optional<Project> projectBOptional = projectRepository.findByName("Проект B");
        Project project2;
        if (projectBOptional.isEmpty()) {
            project2 = new Project();
            project2.setName("Проект B");
            projectRepository.save(project2); // Сохраняем проект B, если его нет
        } else {
            project2 = projectBOptional.get();
        }

        // Создание пользователей
        if (userRepository.findByUsername("Иван").isEmpty()) {
            User user1 = new User();
            user1.setUsername("Иван");
            user1.setEmail("ivan@example.com");
            user1.setRole("USER");
            user1.getProjects().add(project1); // Связываем пользователя с проектом A
            user1.getProjects().add(project2); // Связываем пользователя с проектом B
            userRepository.save(user1);
        }

        if (userRepository.findByUsername("Петр").isEmpty()) {
            User user2 = new User();
            user2.setUsername("Петр");
            user2.setEmail("petr@example.com");
            user2.setRole("USER");
            user2.getProjects().add(project1); // Связываем пользователя с проектом A
            userRepository.save(user2);
        }
    }
}
