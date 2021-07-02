package com.cs.dao;

import com.cs.bean.User;


/**
 * @author zycloud68@163.com
 * @date 2021/1/11 15:21
 */

public interface UserDao {
    /**
     * 根据id信息来查询user数据
     * @param id 传入参数ID
     * @return
     */
    public void selectUser(int id);

    /**
     * @return User
     * 根据id信息来查询User，并且返回User
     */
    public User selectUsers(int id);
}
