package com.derder.admin.rest;

import com.derder.admin.BaseAdminController;
import com.derder.business.dto.EmrgContactDTO;
import com.derder.business.dto.UserDTO;
import com.derder.business.model.EmrgContact;
import com.derder.business.model.User;
import com.derder.business.service.UserService;
import com.derder.business.vo.EmrgContactVO;
import com.derder.business.vo.UserVO;
import com.derder.common.util.JsonUtil;
import com.derder.common.util.ResultData;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController extends BaseAdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/addUser", method= RequestMethod.POST, produces="application/json; charset=UTF-8")
    public @ResponseBody
    ResultData addUser(@RequestBody String json){
        //{"userName":"张三","phoneNumber":"13333311551","email":"zhangsan@126.com","password":"123456","emrgContactList":[{"name":"王五","email":"wangwu@gmail.com","phoneNumber":"15655115511"}]}
        UserVO userVO = JsonUtil.parseObject(json,UserVO.class);
        User user = userVO.convertUser(userVO);
        List<EmrgContactVO> emrgContactVOList = userVO.getEmrgContactList();
        List<EmrgContact> emrgContactList = Lists.newArrayList();
        for (EmrgContactVO emrgContactVO:emrgContactVOList) {
            EmrgContact emrgContact = emrgContactVO.convertEmrgContact(emrgContactVO);
            emrgContactList.add(emrgContact);
        }
        userService.addUserAndEmrgContactList(user,emrgContactList);
        return getResultData(true,"","","");
    }

    @RequestMapping("/user")
    public ResultData getOne(Long userId) {
        User user = userService.getUser(userId);
        List<EmrgContact> list = userService.getEmrgContactListByUser(userId);

        UserDTO userDTO = user.convertDTO(user);
        List<EmrgContactDTO> emrgContactList = Lists.newArrayList();
        for (EmrgContact emrgContact:list) {
            EmrgContactDTO emrgContactDTO = emrgContact.convertDTO(emrgContact);
            emrgContactList.add(emrgContactDTO);
        }
        userDTO.setEmrgContactList(emrgContactList);
        return getResultData(true,userDTO,"","");
    }

    @RequestMapping(value="/updateUser", method= RequestMethod.POST, produces="application/json; charset=UTF-8")
    public @ResponseBody
    ResultData updateUser(@RequestBody String json){
        //{"userName":"张三","phoneNumber":"13333311551","email":"zhangsan@126.com","password":"123456","emrgContactList":[{"name":"王五","email":"wangwu@gmail.com","phoneNumber":"15655115511"}]}
        UserVO userVO = JsonUtil.parseObject(json,UserVO.class);
        User user = userVO.convertUser(userVO);
        long userId = user.getId();
        user.setUpdateBy(userId);
        List<EmrgContactVO> emrgContactVOList = userVO.getEmrgContactList();
        List<EmrgContact> emrgContactList = Lists.newArrayList();
        for (EmrgContactVO emrgContactVO:emrgContactVOList) {
            EmrgContact emrgContact = emrgContactVO.convertEmrgContact(emrgContactVO);
            emrgContact.setUpdateBy(userId);
            emrgContactList.add(emrgContact);
        }
        userService.updateUserAndEmrgContactList(user,emrgContactList);
        return getResultData(true,"","","");
    }
}
