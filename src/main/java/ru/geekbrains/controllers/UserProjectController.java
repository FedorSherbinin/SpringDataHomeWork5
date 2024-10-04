package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.UsersProject;
import ru.geekbrains.services.UserProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/user-projects")
public class UserProjectController {

    private final UserProjectService userProjectService;

    @Autowired
    public UserProjectController(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }

    @GetMapping("/project/{projectId}/users")
    public ResponseEntity<List<UsersProject>> getUsersByProjectId(@PathVariable Long projectId) {
        List<UsersProject> users = userProjectService.getUsersByProjectId(projectId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{userId}/projects")
    public ResponseEntity<List<UsersProject>> getProjectsByUserId(@PathVariable Long userId) {
        List<UsersProject> projects = userProjectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUserToProject(@RequestParam Long userId, @RequestParam Long projectId) {
        userProjectService.addUserToProject(userId, projectId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeUserFromProject(@RequestParam Long userId, @RequestParam Long projectId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/add")
    public String showAddUserToProjectForm() {
        return "addUserToProject"; // Имя вашего HTML-шаблона
    }
}
