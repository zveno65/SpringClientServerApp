package ru.plotnikov.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.plotnikov.exception.UserAccountException;
import ru.plotnikov.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    User findById(Long id);
    User addUser(User user) throws UserAccountException;
    User updateUser(User user);
    User loadUserByUsername(String name);
}
