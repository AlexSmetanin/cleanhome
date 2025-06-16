package chpt.cleanhome.service;

import chpt.cleanhome.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

}
