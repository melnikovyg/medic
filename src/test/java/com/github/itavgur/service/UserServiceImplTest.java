package com.github.itavgur.service;

import com.github.itavgur.utils.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static com.github.itavgur.TestDataTemplate.*;
import static com.github.itavgur.TestUtils.assertMatcher;


public class UserServiceImplTest extends BaseServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() {
        service.create(USER_CREATED);
        assertMatcher(service.getAll(), Arrays.asList(USER_ADMIN, USER_REGISTRATOR, USER_CREATED));
    }

    @Test
    public void get() {
        assertMatcher(service.get(USER_REGISTRATOR.getId()), USER_REGISTRATOR);
    }

    @Test(expected = NotFoundException.class)
    public void getNonExistent() {
        service.get(INVALID_ID);
    }

    @Test
    public void getByPhone() {
        assertMatcher(service.getByPhone(USER_REGISTRATOR.getPhone()), Arrays.asList(USER_ADMIN, USER_REGISTRATOR));
    }

    @Test(expected = NotFoundException.class)
    public void getByPhoneNonExistent() {
        service.getByPhone(INVALID_STRING);
    }

    @Test
    public void getAll() {
        assertMatcher(service.getAll(), Arrays.asList(USER_ADMIN, USER_REGISTRATOR));
    }

    @Test
    public void update() {
        service.update(USER_REGISTRATOR_UPDATED);
        assertMatcher(service.getAll(), Arrays.asList(USER_ADMIN, USER_REGISTRATOR_UPDATED));
    }

    @Test
    public void delete() {
        service.delete(USER_ADMIN.getId());
        assertMatcher(service.getAll(), Arrays.asList(USER_REGISTRATOR));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNonExistent() {
        service.delete(INVALID_ID);
    }
}