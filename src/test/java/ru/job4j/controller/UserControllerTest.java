package ru.job4j.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dto.FileDto;
import ru.job4j.model.*;
import ru.job4j.model.Candidate;
import ru.job4j.service.CandidateService;
import ru.job4j.service.CityService;
import ru.job4j.service.CandidateService;
import ru.job4j.service.UserService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class UserControllerTest {
    private UserService userService;
    private UserController userController;

    @BeforeEach
    public void initServices() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @DisplayName("Проверка метода register")
    @Test
    public void checkRegister() {
        var user = new User(1, "email", "name", "password");
        when(userService.save(user)).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var view = userController.register(model, user);

        assertThat(view).isEqualTo("redirect:/vacancies");
    }

    @DisplayName("Проверка метода register с ошибкой")
    @Test
    public void checkRegisterWithError() {
        var expectedException = new RuntimeException("Пользователь с такой почтой уже существует");
        when(userService.save(any())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = userController.register(model, new User());
        var actualExceptionMessage = model.getAttribute("message");

        assertThat(view).isEqualTo("errors/404");
        assertThat(actualExceptionMessage).isEqualTo(expectedException.getMessage());
    }

    @Test
    public void whenRequestToLogin() {
        var user = new User(1, "email", "name", "password");
        var request = mock(HttpServletRequest.class);
        var httpSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(httpSession);
        when(userService.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var view = userController.loginUser(user, model, request);
        assertThat(view).isEqualTo("redirect:/vacancies");
    }

    @Test
    public void whenRequestToLoginButWrongLoginOrPassword() {
        var user = new User(1, "email", "name", "password");
        var error = "Почта или пароль введены неверно";
        var request = mock(HttpServletRequest.class);
        when(userService.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = userController.loginUser(user, model, request);
        var actualError = model.getAttribute("error");

        assertThat(view).isEqualTo("users/login");
        assertThat(actualError).isEqualTo(error);
    }

    @Test
    public void whenRequestLogoutPage() {
        var httpSession = mock(HttpSession.class);
        var view = userController.logout(httpSession);
        assertThat(view).isEqualTo("redirect:/users/login");
    }
}