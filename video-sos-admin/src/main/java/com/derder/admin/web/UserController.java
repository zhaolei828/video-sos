package com.derder.admin.web;

import com.derder.base.BaseController;
import com.derder.business.model.User;
import com.derder.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @RequestMapping("/users")
    public String userList(Map<String, Object> model) {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<User> users = userService.listBySearch(null, null, null, null, null, pageRequest);
        model.put("users",users);
        return "users";
    }
}
