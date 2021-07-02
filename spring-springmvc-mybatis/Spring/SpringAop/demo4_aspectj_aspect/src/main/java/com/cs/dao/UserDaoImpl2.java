package com.cs.dao;

import com.cs.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author zycloud68@163.com
 * @date 2021/1/11 15:23
 */
@Repository
public class UserDaoImpl2 implements  UserDao2{

    @Override
    public int queryUserCount(String name) {
        System.out.println("正在使用dao层");
        if ("zhangyan".equals(name)){
            return 666;
        }else {
            return 0;
        }
    }
}
