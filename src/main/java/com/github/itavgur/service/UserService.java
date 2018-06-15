package com.github.itavgur.service;


import com.github.itavgur.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User get(int id);

    List<User> getByPhone(String phone);

    List<User> getAll();

    User update(User user);

    boolean delete(int id);

}
