package com.github.itavgur.service;

import com.github.itavgur.model.User;
import com.github.itavgur.repository.UserRepository;
import com.github.itavgur.utils.NotFoundException;
import com.github.itavgur.utils.UserAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.itavgur.utils.Checkers.checkNonNull;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User get(int id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> getByPhone(String phone) {
        return checkNonNull(repository.findByPhone(phone), "get by phone " + phone);
    }

    @Override
    public List<User> getAll() {
        return checkNonNull(repository.findAll(), "get all");
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return checkNonNull(repository.delete(id), "delete user id" + id);
    }

    @Override
    public UserAuthenticated loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User " + login + " is not found"));
        return new UserAuthenticated(user);
    }

}
