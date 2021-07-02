package com.cs.dao;

import org.springframework.stereotype.Repository;

/**
 * @author zycloud68@163.com
 * @date 2021/1/8 23:29
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void selectUser(int id) {
        System.out.println("正在调用dao层的代码");
    }
}
