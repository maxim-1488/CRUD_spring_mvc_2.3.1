package ru.maximkrylov.spring_mvc.service;

import ru.maximkrylov.spring_mvc.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void removeUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();
}
