package com.cs.dao;

import com.cs.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author zycloud68@163.com
 * @date 2021/1/11 15:23
 */
@Repository
public class UserDaoImpl  implements  UserDao{
    @Override
    public void selectUser(int id) {
        System.out.println("正在根据id信息来调用dao层");
    }

    @Override
    public User selectUsers(int id) {
        User user = new User();
        int i = 1/0;
        return user;
    }
}
