package com.github.itavgur.controller.users;

import com.github.itavgur.controller.BaseController;
import com.github.itavgur.service.UserService;
import com.github.itavgur.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.itavgur.utils.Converters.userAsUserTo;


@RestController
@RequestMapping(value = UserAdminRestController.REST_URL, produces = "application/json;charset=UTF-8")
public class UserAdminRestController extends BaseController {

    public static final String REST_URL = "/rest/admin/users";

    @Autowired
    private UserService service;

    @GetMapping("/")
    public List<UserTo> getAll() {
        log.info("get all");
        return userAsUserTo(service.getAll());
    }

    @GetMapping("/{id}")
    public UserTo getById(@PathVariable("id") int id) {
        log.info("get by id {}", id);
        return userAsUserTo(service.get(id));

    }

}
