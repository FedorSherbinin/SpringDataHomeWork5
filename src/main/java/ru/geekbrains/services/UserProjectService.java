package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.UsersProject;
import ru.geekbrains.repositories.UsersProjectRepository;

import java.util.List;

@Service
public class UserProjectService {

    private final UsersProjectRepository usersProjectRepository;

    @Autowired
    public UserProjectService(UsersProjectRepository usersProjectRepository) {
        this.usersProjectRepository = usersProjectRepository;
    }

    public List<UsersProject> getUsersByProjectId(Long projectId) {
        return usersProjectRepository.findAll().stream()
                .filter(up -> up.getProjectId().equals(projectId))
                .toList();
    }

    public List<UsersProject> getProjectsByUserId(Long userId) {
        return usersProjectRepository.findAll().stream()
                .filter(up -> up.getUserId().equals(userId))
                .toList();
    }

    public void addUserToProject(Long userId, Long projectId) {
        UsersProject usersProject = new UsersProject(null, projectId, userId);
        usersProjectRepository.save(usersProject);
    }

    public void removeUserFromProject(Long userId, Long projectId) {
        List<UsersProject> userProjects = usersProjectRepository.findAll().stream()
                .filter(up -> up.getUserId().equals(userId) && up.getProjectId().equals(projectId))
                .toList();

        usersProjectRepository.deleteAll(userProjects);
    }
}
