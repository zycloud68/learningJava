package com.cs.dao;

import org.springframework.stereotype.Repository;

/**
 * @author zycloud68@163.com
 * @date 2021/1/8 22:49
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void selectUser(int id) {
        System.out.println("正在使用dao层");
    }
}
