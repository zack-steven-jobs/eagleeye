package com.cmsz.eagleeye.controler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsz.eagleeye.command.UserInfoCommand;
import com.cmsz.eagleeye.exception.ServiceLevelException;
import com.cmsz.eagleeye.model.PageModel;
import com.cmsz.eagleeye.model.UserInfoModel;
import com.cmsz.eagleeye.service.IUserInfoService;
import com.cmsz.eagleeye.util.Message;
import com.cmsz.eagleeye.util.ToolUtils;

/**
 * 用户管理控制层类
 * 
 * @author LC
 * @date:2015-2-12
 * 
 */
@Controller
public class UserInfoController {
    private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private IUserInfoService userInfoService;
    
    /**
     * 查询规则List
     * 
     * @param alarm
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/usr/list", method = RequestMethod.POST)
    public Message<Object> findUserInfoList(@RequestBody UserInfoCommand userInfo) {
        Message<Object> message = new Message<>();
        try {
            if (!ToolUtils.isEmpty(userInfo)) {
                PageModel<UserInfoModel> pageList = null;
                UserInfoModel userInfoModel = new UserInfoModel();
                BeanUtils.copyProperties(userInfo, userInfoModel);
                pageList = userInfoService.selectByCondition(userInfoModel);
                Map<String, Object> alarmMap = new HashMap<>();
                alarmMap.put("page", pageList.getTotalPageCount());
                alarmMap.put("list", pageList.getResultList());
                message.setData(alarmMap);
                message.setSuccess(true);
                message.setMessage("查询成功!");
            }
        } catch (ServiceLevelException e) {
            message.setMessage(e.getMessage());
            message.setSuccess(false);
            logger.info("查询规则失败,信息为：{}", e);
        }
        return message;
    }

}
