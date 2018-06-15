package com.github.itavgur.utils;

import com.github.itavgur.model.User;
import com.github.itavgur.to.UserTo;

import java.util.ArrayList;
import java.util.List;

public class Converters {

    public static UserTo userAsUserTo(User user) {
        return new UserTo(user.getId(), user.getLogin(), user.getPassword(), user.getFullName(),
                user.getPhone(), user.isEnabled(), user.getRoles());
    }

    public static List<UserTo> userAsUserTo(List<User> users) {
        ArrayList<UserTo> usersTo = new ArrayList<>();
        users.forEach(e -> usersTo.add(userAsUserTo(e)));
        return usersTo;
    }

}
