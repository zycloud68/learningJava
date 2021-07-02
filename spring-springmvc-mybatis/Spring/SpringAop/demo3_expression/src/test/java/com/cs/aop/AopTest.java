package com.cs.aop;

import com.cs.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zycloud68@163.com
 * @date 2021/1/9 01:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class AopTest {
    @Autowired
    UserDao userDao;

    @Test
    public void aopTest(){
        userDao.selectUser(3);
    }
}
