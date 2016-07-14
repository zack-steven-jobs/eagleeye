package com.cmsz.eagleeye.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cmsz.eagleeye.dao.UserInfoDao;
import com.cmsz.eagleeye.exception.DaoLevelException;
import com.cmsz.eagleeye.exception.ServiceLevelException;
import com.cmsz.eagleeye.model.PageModel;
import com.cmsz.eagleeye.model.UserInfoModel;
import com.cmsz.eagleeye.service.IUserInfoService;
import com.cmsz.eagleeye.util.ToolUtils;

/**
 * 用户管理Service实现类
 * 
 * @author LC
 * @date:2015-2-12
 * 
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    private Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Resource
    private UserInfoDao userInfoDao;
    
    @Override
    public int insert(UserInfoModel record) throws ServiceLevelException {
        int result = 0;
        try {
            result = userInfoDao.insert(record);
        } catch (DaoLevelException e) {
            logger.error("新增失败,错误信息：{}", e);
            throw new ServiceLevelException(e);
        }
        return result;
    }
    @Override
    public int deleteByPrimaryKey(String ids) throws ServiceLevelException {
        int result = 0;
        List<String> idList = Arrays.asList(ids.split(","));
        try {
            result = userInfoDao.deleteByPrimaryKey(idList);
        } catch (DaoLevelException e) {
            logger.error("删除失败,失败原因：{}", e.getMessage());
            throw new ServiceLevelException(e);
        }
        return result;
    }

    @Override
    public int update(UserInfoModel record) throws ServiceLevelException {
        int result = 0;
        try {
            result = userInfoDao.update(record);
        } catch (DaoLevelException e) {
            logger.error("修改失败,错误信息为：{}", e);
            throw new ServiceLevelException(e);
        }
        return result;
    }
  
    @Override
    public UserInfoModel selectByPrimaryKey(Integer id) throws ServiceLevelException {
        UserInfoModel alarmRule = new UserInfoModel();
        try {
            alarmRule = userInfoDao.selectByPrimaryKey(id);
        } catch (DaoLevelException e) {
            logger.error("查询失败,信息为：{}", e);
            throw new ServiceLevelException(e);
        }
        return alarmRule;
    }


    @Override
    public PageModel<UserInfoModel> selectByCondition(UserInfoModel record)
            throws ServiceLevelException {
        record.initGoTopIndex();// 初始分页
        List<UserInfoModel> list = new ArrayList<UserInfoModel>();
        int totalCount = 0;
        PageModel<UserInfoModel> pageModel = new PageModel<UserInfoModel>();

        try {
            totalCount = userInfoDao.selectCount(record);
            list = userInfoDao.selectByCondition(record);
            pageModel.setTotalPageCount(ToolUtils.isEmpty(totalCount) == true
                    ? "0"
                    : String.valueOf(totalCount));
            pageModel.setResultList(list);
        } catch (DaoLevelException e) {
            logger.error("按条件查询失败，错误信息：{}", e);
            throw new ServiceLevelException(e);
        }
        return pageModel;
    }

    @Override
    public boolean checkIsExist(String account) throws ServiceLevelException {
        int result = 0;
        try {
            result = userInfoDao.checkIsExist(account);
        } catch (DaoLevelException e) {
            logger.error("重名验证失败，错误信息：{}", e);
            throw new ServiceLevelException(e);
        }
        return result > 0 ? true : false;
    }
}
