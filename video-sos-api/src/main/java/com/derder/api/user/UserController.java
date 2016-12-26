package com.derder.api.user;

import com.derder.admin.BaseApiController;
import com.derder.business.dto.EmrgContactDTO;
import com.derder.business.dto.UserDTO;
import com.derder.business.model.EmrgContact;
import com.derder.business.model.User;
import com.derder.business.service.LoginService;
import com.derder.business.service.UserService;
import com.derder.business.vo.EmrgContactVO;
import com.derder.business.vo.UserVO;
import com.derder.common.exception.BusinessException;
import com.derder.common.redis.CacheService;
import com.derder.common.util.ErrorCode;
import com.derder.common.util.JsonUtil;
import com.derder.common.util.ResultData;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
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
public class UserController extends BaseApiController {
    private final Logger log = Logger.getLogger(getClass());
    @Autowired
    UserService userService;

    @Autowired
    CacheService cacheService;

    @Autowired
    LoginService loginService;

    @RequestMapping(value="/doRegister", method= RequestMethod.POST, produces="application/json; charset=UTF-8")
    public @ResponseBody
    ResultData handleRegister(@RequestBody String json){
        //{"userName":"张三","phoneNumber":"13333311551","email":"zhangsan@126.com","password":"123456","emrgContactList":[{"name":"王五","email":"wangwu@gmail.com","phoneNumber":"15655115511"}]}
        UserVO userVO = JsonUtil.parseObject(json,UserVO.class);
        User user = userVO.convertUser(userVO);
        List<EmrgContactVO> emrgContactVOList = userVO.getEmrgContactList();
        List<EmrgContact> emrgContactList = Lists.newArrayList();
        for (EmrgContactVO emrgContactVO:emrgContactVOList) {
            EmrgContact emrgContact = emrgContactVO.convertEmrgContact(emrgContactVO);
            emrgContactList.add(emrgContact);
        }
        try {
            userService.addUserAndEmrgContactList(user,emrgContactList);
        } catch (BusinessException e) {
            if (null != e.getErrorCode() && e.getErrorCode().equals(ErrorCode.USER_REG_EXCEPTION)){
                return getResultData(false,"",ErrorCode.USER_REG_EXCEPTION);
            }
            log.error(e);
        } catch (Exception e){
            log.error("####注册信息保存失败",e);
            return getResultData(false,"",ErrorCode.SYSTEM_ERROR);
        }
        return getResultData(true,"","","");
    }

    @RequestMapping(value="/updateUserInfo", method= RequestMethod.POST, produces="application/json; charset=UTF-8")
    public @ResponseBody
    ResultData updateUserInfo(@RequestBody String json){
        //{"id":811816699479855100,"userName":"张三","phoneNumber":"13333311551","email":"zhangsan@126.com","password":"123456","emrgContactList":[{"id":811816699928645600,"name":"王五","email":"wangwu@gmail.com","phoneNumber":"15655115511"}]}
        long userId = getUserId();
        UserVO userVO = JsonUtil.parseObject(json,UserVO.class);
        User user = userVO.convertUser(userVO);
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

    @RequestMapping(value="/testCache", produces = "application/json; charset=UTF-8")
    ResultData testCache(){
        cacheService.add("sss","ddd");
        return getResultData(true,"","","");
    }

    @RequestMapping(value="/doLogin",method= RequestMethod.POST, produces = "application/json; charset=UTF-8")
    ResultData handleLogin(String phone,String password){

        String token = loginService.login(phone,password);
        if (null != token){
            return getResultData(true,token,"","");
        }else {
            log.error("####"+ErrorCode.USERNAME_PASSORD_ERROR+"\n"
            +"param==> phone:"+phone+" | password:"+password);
        }
        return getResultData(false,"", ErrorCode.USERNAME_PASSORD_ERROR);
    }

    @RequestMapping(value="/getUserInfo", produces = "application/json; charset=UTF-8")
    ResultData getUserInfo(){
        //return:{"succeed":true,"errorCode":"","errorMsg":"","data":{"id":232342342342,"userName":"张三","phoneNumber":"13333311551","email":"zhangsan@126.com","emrgContactList":[{"name":"王五","email":"wangwu@gmail.com","phoneNumber":"15655115511"}]}}
        User user = getUser();
        List<EmrgContact> list = userService.getEmrgContactListByUser(user.getId());
        UserDTO userDTO = user.convertDTO(user);
        List<EmrgContactDTO> emrgContactList = Lists.newArrayList();
        for (EmrgContact emrgContact:list) {
            EmrgContactDTO emrgContactDTO = emrgContact.convertDTO(emrgContact);
            emrgContactList.add(emrgContactDTO);
        }
        userDTO.setEmrgContactList(emrgContactList);
        return getResultData(true,userDTO,"","");
    }
}
