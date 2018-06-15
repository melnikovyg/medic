package com.github.itavgur.to;

import com.github.itavgur.model.BaseEntity;
import com.github.itavgur.model.Role;

import java.util.Set;

public class UserTo extends BaseEntity {

    private String login;
    private String password;
    private String fullName;
    private String phone;
    private boolean enabled;
    private Set<Role> roles;

    public UserTo(Integer id, String login, String password, String fullName, String phone, boolean enabled, Set<Role> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.enabled = enabled;
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
