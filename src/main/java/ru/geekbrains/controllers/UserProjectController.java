package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Project;
import ru.geekbrains.entities.User;
import ru.geekbrains.repositories.ProjectRepository;
import ru.geekbrains.repositories.UserRepository;
import ru.geekbrains.services.UserProjectService;

import java.util.List;
import java.util.Set;

@Controller
public class UserProjectController {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserProjectService userProjectService;

    public UserProjectController(UserRepository userRepository, ProjectRepository projectRepository, UserProjectService userProjectService) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.userProjectService = userProjectService;
    }

    // Отображение формы для добавления пользователя
    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser"; // Имя шаблона Thymeleaf
    }

    // Обработка добавления пользователя
    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users"; // Перенаправление на страницу со списком пользователей
    }

    // Отображение формы для добавления проекта
    @GetMapping("/addProject")
    public String showAddProjectForm(Model model) {
        model.addAttribute("project", new Project());
        return "addProject"; // Имя шаблона Thymeleaf
    }

    // Обработка добавления проекта
    @PostMapping("/addProject")
    public String addProject(@ModelAttribute Project project) {
        projectRepository.save(project);
        return "redirect:/projects"; // Перенаправление на страницу со списком проектов
    }

    // Страница со списком пользователей
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList"; // Имя шаблона Thymeleaf
    }

    // Страница со списком проектов
    @GetMapping("/projects")
    public String listProjects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "projectList"; // Имя шаблона Thymeleaf
    }

    // Отображение формы для добавления пользователя в проект
    @GetMapping("/api/users/projects/add")
    public String showAddUserToProjectForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());
        return "addUserToProject"; // Имя шаблона Thymeleaf
    }

    // Обработка добавления пользователя в проект
    @PostMapping("/api/users/projects/add")
    public String addUserToProject(@RequestParam("userId") Long userId, @RequestParam("projectId") Long projectId) {
        userProjectService.addUserToProject(userId, projectId); // Метод для добавления пользователя в проект
        return "redirect:/"; // Перенаправление на главную страницу после успешного добавления
    }

    // Удаление пользователя
    @GetMapping("/api/users/delete")
    public String showDeleteUserForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "deleteUser"; // Имя шаблона Thymeleaf для удаления пользователя
    }

    @PostMapping("/api/users/delete")
    public String deleteUser(@RequestParam("userId") Long userId) {
        userRepository.deleteById(userId);
        return "redirect:/users"; // Перенаправление на страницу со списком пользователей
    }

    // Удаление проекта
    @GetMapping("/api/projects/delete")
    public String showDeleteProjectForm(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "deleteProject"; // Имя шаблона Thymeleaf для удаления проекта
    }

    @PostMapping("/api/projects/delete")
    public String deleteProject(@RequestParam("projectId") Long projectId) {
        projectRepository.deleteById(projectId);
        return "redirect:/projects"; // Перенаправление на страницу со списком проектов
    }

    // Вывод всех проектов пользователя
    @GetMapping("/users/{id}/projects")
    public String getUserProjects(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")); // Использование orElseThrow() на Optional
        Set<Project> projects = user.getProjects();
        model.addAttribute("user", user);
        model.addAttribute("projects", projects);
        return "userProjects"; // Возвращаем имя шаблона
    }

    // Вывод всех пользователей проекта
    @GetMapping("/projects/{id}/users")
    public String getProjectUsers(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found")); // Использование orElseThrow() на Optional
        Set<User> users = project.getUsers();
        model.addAttribute("project", project);
        model.addAttribute("users", users);
        return "projectUsers"; // Возвращаем имя шаблона
    }
}
