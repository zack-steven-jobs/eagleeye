package com.cmsz.eagleeye.service;

import java.util.List;
import java.util.Map;

import com.cmsz.eagleeye.exception.DaoLevelException;
import com.cmsz.eagleeye.exception.ServiceLevelException;
import com.cmsz.eagleeye.model.PageModel;
import com.cmsz.eagleeye.model.UserInfoModel;

/**
 * 用户管理Service
 * 
 * @author LC
 * @date:2015-2-12
 * 
 */
public interface IUserInfoService {
	/**
	 * 新增一条
	 * 
	 * @param record
	 * @return
	 * @throws ServiceLevelException
	 */
	int insert(UserInfoModel record) throws ServiceLevelException;
	
	/**
	 * 删除一条或多条
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceLevelException
	 */
	int deleteByPrimaryKey(String userIds) throws ServiceLevelException;
	/**
	 * 修改一条规则
	 * 
	 * @param record
	 * @return
	 * @throws ServiceLevelException
	 */
	int update(UserInfoModel record) throws ServiceLevelException;
	/**
	 * 通过主键查询一条
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceLevelException
	 */
	UserInfoModel selectByPrimaryKey(Integer userId) throws ServiceLevelException;

	/**
	 * 根据条件查询记录或全部记录
	 * 
	 * @param record
	 * @return
	 * @throws ServiceLevelException
	 */
	PageModel<UserInfoModel> selectByCondition(UserInfoModel record)
			throws ServiceLevelException;
	
	/**
	 * 验证是否重名
	 * 
	 * @param accountId
	 * @return
	 */
	boolean checkIsExist(String accountId) throws ServiceLevelException;
}
