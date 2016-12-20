package derder.admin.web;

import com.derder.base.BaseController;
import com.derder.business.model.User;
import com.derder.business.service.LoginService;
import com.derder.business.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class UserController extends BaseController {
    @Autowired
//    UserService userService;
    LoginService loginService;

    @RequestMapping("/users")
    public String userList(Map<String, Object> model) {
//        PageRequest pageRequest = new PageRequest(0, 10);
//        Page<User> users = userService.listBySearch(null, null, null, null, null, pageRequest);
        List<User> users = Lists.newArrayList();
        model.put("users",users);
        return "users";
    }
}
