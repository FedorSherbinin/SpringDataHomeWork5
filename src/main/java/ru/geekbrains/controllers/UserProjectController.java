package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.UsersProject;
import ru.geekbrains.services.UserProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class UserProjectController {

    private final UserProjectService userProjectService;

    @Autowired
    public UserProjectController(UserProjectService userProjectService) {
        this.userProjectService = userProjectService;
    }

    @GetMapping("/{projectId}/users")
    public ResponseEntity<List<UsersProject>> getUsersByProjectId(@PathVariable Long projectId) {
        List<UsersProject> users = userProjectService.getUsersByProjectId(projectId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userId}/projects")
    public ResponseEntity<List<UsersProject>> getProjectsByUserId(@PathVariable Long userId) {
        List<UsersProject> projects = userProjectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/{projectId}/users/{userId}")
    public ResponseEntity<Void> addUserToProject(@PathVariable Long userId, @PathVariable Long projectId) {
        userProjectService.addUserToProject(userId, projectId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{projectId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable Long userId, @PathVariable Long projectId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.ok().build();
    }
}
