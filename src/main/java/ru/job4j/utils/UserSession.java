package ru.job4j.utils;

import org.springframework.ui.Model;
import ru.job4j.model.User;

import javax.servlet.http.HttpSession;

public final class UserSession {
    private UserSession() {
    }

    public static User setUserFromSession(Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
            session.setAttribute("user", user);
        }
        model.addAttribute("user", user);
        return user;
    }
}
