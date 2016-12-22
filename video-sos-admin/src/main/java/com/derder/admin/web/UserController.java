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
    public String userList(Map<String, Object> model,
                           Integer pageNumber,Integer pageSize) {
        if (null == pageNumber || pageNumber<=0){
            pageNumber = super.pageNumber;
        }
        if (null == pageSize || pageSize <= 0){
            pageSize = super.pageSize;
        }
        if(pageSize > 100){
            pageSize = 100;
        }
        PageRequest pageRequest = new PageRequest(pageNumber-1, pageSize);
        Page<User> pageUsers = userService.listBySearch(null, null, null, null, null, pageRequest);
        model.put("pageUsers",pageUsers);
        return "users";
    }
}
