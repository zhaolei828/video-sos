package com.derder.api.user;

import com.derder.business.model.EmrgContact;
import com.derder.business.model.User;
import com.derder.business.service.UserService;
import com.derder.business.vo.EmrgContactVO;
import com.derder.business.vo.UserVO;
import com.derder.base.BaseController;
import com.derder.common.redis.CacheService;
import com.derder.common.util.BeanUtil;
import com.derder.common.util.JsonUtil;
import com.derder.common.util.ResultData;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhaolei
 * Date: 16-12-17
 * Time: 上午1:16
 */
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @Autowired
    CacheService cacheService;

    @RequestMapping(value="/doRegister", method= RequestMethod.POST, produces="application/json")
    public @ResponseBody
    ResultData handleRegister(@RequestBody String json){
        UserVO userVO = JsonUtil.parseObject(json,UserVO.class);
        User user = BeanUtil.convertUser(userVO);
        List<EmrgContactVO> emrgContactVOList = userVO.getEmrgContactList();
        List<EmrgContact> emrgContactList = Lists.newArrayList();
        for (EmrgContactVO emrgContactVO:emrgContactVOList) {
            EmrgContact emrgContact = BeanUtil.convertEmrgContact(emrgContactVO);
            emrgContactList.add(emrgContact);
        }
        userService.addUserAndEmrgContactList(user,emrgContactList);
        return getResultData(true,"","","");
    }

    @RequestMapping(value="/testCache")
    ResultData testCache(){
        cacheService.add("sss","ddd");
        return getResultData(true,"","","");
    }
}
