package com.derder.api.user;

import com.derder.base.BaseController;
import com.derder.common.util.ResultData;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhaolei
 * Date: 16-12-17
 * Time: 上午1:16
 */
@RestController
public class UserController extends BaseController {
    @RequestMapping(value="/doRegister", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody
    ResultData handleRegister(@RequestBody String json){

        return getResultData(true,"","","");
    }
}
