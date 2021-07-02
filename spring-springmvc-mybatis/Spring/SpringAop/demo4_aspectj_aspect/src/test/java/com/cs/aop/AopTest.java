package com.cs.aop;

import com.cs.dao.UserDao;
import com.cs.dao.UserDao2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zycloud68@163.com
 * @date 2021/1/11 16:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class AopTest {
    @Autowired
    UserDao userDao;
    @Autowired
    UserDao2 userDao2;
    @Test
    public void aopTest(){
        userDao.selectUser(1);
        userDao.selectUsers(1);
    }

    @Test
    public void annotationTest(){
        int name = userDao2.queryUserCount("zhangyan88");
        System.out.println(name);

    }
}
