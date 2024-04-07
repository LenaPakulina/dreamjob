package ru.job4j.repository;

import ru.job4j.model.User;
import ru.job4j.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

    void deleteAll();
}
