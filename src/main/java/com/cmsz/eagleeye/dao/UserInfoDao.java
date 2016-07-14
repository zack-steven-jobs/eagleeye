package com.cmsz.eagleeye.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cmsz.eagleeye.exception.DaoLevelException;
import com.cmsz.eagleeye.model.User;
import com.cmsz.eagleeye.model.UserInfoModel;

/**
 * 用户管理DAO
 * 
 * @author LC
 * @date:2015-2-12
 * 
 */
@Repository
public interface UserInfoDao {
  /**
   * 新增一条
   * 
   * @param record
   * @return
   * @throws DaoLevelException
   */
  int insert(UserInfoModel record) throws DaoLevelException;

  int insertUser(User user);

  /**
   * 删除一条或多条
   * 
   * @param userId
   * @return
   * @throws DaoLevelException
   */
  int deleteByPrimaryKey(List<String> userIds) throws DaoLevelException;

  /**
   * 修改一条规则
   * 
   * @param record
   * @return
   * @throws DaoLevelException
   */
  int update(UserInfoModel record) throws DaoLevelException;

  /**
   * 通过主键查询一条
   * 
   * @param userId
   * @return
   * @throws DaoLevelException
   */
  UserInfoModel selectByPrimaryKey(Integer userId) throws DaoLevelException;

  /**
   * 根据条件查询记录或全部记录
   * 
   * @param record
   * @return
   * @throws DaoLevelException
   */
  List<UserInfoModel> selectByCondition(UserInfoModel record) throws DaoLevelException;


  /**
   * 根据条件查询记录数
   * 
   * @param record
   * @return
   * @throws DaoLevelException
   */
  int selectCount(UserInfoModel record) throws DaoLevelException;

  /**
   * 验证是否重名
   * 
   * @param accountId
   * @return
   */
  int checkIsExist(String accountId) throws DaoLevelException;
}
