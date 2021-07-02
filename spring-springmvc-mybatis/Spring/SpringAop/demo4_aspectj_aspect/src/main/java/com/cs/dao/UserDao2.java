package com.cs.dao;

/**
 * @author zycloud68@163.com
 * @date 2021/1/11 17:04
 */
public interface UserDao2 {
    /**
     * @param name 根据name值来查找User的数量
     * @return
     */
    int  queryUserCount(String name);
}
