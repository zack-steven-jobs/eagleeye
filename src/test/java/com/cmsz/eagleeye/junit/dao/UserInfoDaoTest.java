package com.cmsz.eagleeye.junit.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cmsz.eagleeye.dao.UserInfoDao;
import com.cmsz.eagleeye.exception.DaoLevelException;
import com.cmsz.eagleeye.model.User;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class UserInfoDaoTest {
  @Resource
  private UserInfoDao userInfoDao;

  @Test
  public void insert(User user) throws DaoLevelException {
    user.setState(13);
    user.setNickname("hello");
    int result = userInfoDao.insertUser(user);
    System.out.println(">>>>>>>>>>>>>>>" + result);
  }
}
