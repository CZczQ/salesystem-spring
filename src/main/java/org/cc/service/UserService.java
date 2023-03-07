package org.cc.service;

import org.cc.pojo.User;
import org.cc.pojo.Vip;

import java.sql.SQLException;

/**
 * @Author cc
 * @Date 2022/10/13 19:25
 * @PackageName:org.cc.service
 * @ClassName: UserService
 * @Description: TODO
 * @Version 1.0
 */
public interface UserService {
    User login(String username, String password) throws SQLException;
    void register(Vip vip) ;
    Vip QiantaiLogin(String username, String password) throws SQLException;
}
