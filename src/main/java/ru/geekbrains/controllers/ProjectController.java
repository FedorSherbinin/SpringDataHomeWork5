package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Project;
import ru.geekbrains.entities.User;
import ru.geekbrains.repositories.ProjectRepository;
import ru.geekbrains.services.ProjectService;
import ru.geekbrains.services.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/users-projects/add")
public class ProjectController {

    private final ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Метод для получения всех проектов
    @GetMapping("/projects")
    public String getAllProjects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projectList"; // Имя шаблона для списка проектов
    }

    // Метод для получения пользователей проекта по его ID
    @GetMapping("/projects/{id}/users")
    public String getProjectUsers(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Set<User> users = project.getUsers();

        model.addAttribute("project", project);
        model.addAttribute("users", users);
        return "projectUsers"; // Имя шаблона для пользователей проекта
    }

    // Метод для создания нового проекта
    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.ok(savedProject);
    }
}
