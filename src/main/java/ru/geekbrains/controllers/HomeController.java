package ru.geekbrains.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.entities.Project;
import ru.geekbrains.entities.User;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(Model model) {
        // Пример создания временных объектов для теста
        Project project = new Project(); // Здесь должен быть реальный объект
        project.setId(1L); // Пример ID проекта

        User user = new User(); // Здесь должен быть реальный объект
        user.setId(1L); // Пример ID пользователя

        model.addAttribute("project", project);
        model.addAttribute("user", user);

        return "home"; // Вернуть шаблон home.html
    }
}
