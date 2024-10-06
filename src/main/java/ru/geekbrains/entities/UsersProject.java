package ru.geekbrains.entities;

import jakarta.persistence.*;

@Entity
public class UsersProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Указывает на связь многие к одному
    @JoinColumn(name = "user_id", nullable = false) // Идентификатор пользователя, не может быть null
    private User user;

    @ManyToOne // Указывает на связь многие к одному
    @JoinColumn(name = "project_id", nullable = false) // Идентификатор проекта, не может быть null
    private Project project;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
