package org.cc.dao;

import org.cc.pojo.User;
import org.cc.pojo.Vip;

import java.sql.SQLException;
import java.util.Map;

/**
 * @Author cc
 * @Date 2022/10/13 19:27
 * @PackageName:org.cc.dao
 * @ClassName: UserDao
 * @Description: TODO
 * @Version 1.0
 */
public interface UserDao {
    User login(Map map) throws SQLException;
    void register(Vip vip) ;
    Vip QiantaiLogin(Map map) throws SQLException;
}
