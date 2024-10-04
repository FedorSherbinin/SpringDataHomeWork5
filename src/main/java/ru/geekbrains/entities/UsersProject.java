package ru.geekbrains.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_projects")
public class UsersProject extends EntityWithRelation {

    private Long projectId;
    private Long userId;

    // Конструктор с тремя параметрами
    public UsersProject(Long id, Long projectId, Long userId) {
        super(id, null); // Передача id в абстрактный класс EntityWithRelation
        this.projectId = projectId;
        this.userId = userId;
    }
}
