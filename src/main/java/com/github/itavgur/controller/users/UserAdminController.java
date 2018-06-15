package com.github.itavgur.controller.users;

import com.github.itavgur.controller.BaseController;
import com.github.itavgur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = UserAdminController.REST_URL)
public class UserAdminController extends BaseController {

    public static final String REST_URL = "/";

    @Autowired
    private UserService service;

}
