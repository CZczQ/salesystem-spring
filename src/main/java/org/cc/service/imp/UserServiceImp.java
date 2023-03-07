package org.cc.service.imp;


import org.cc.dao.UserDao;
import org.cc.pojo.User;
import org.cc.pojo.Vip;
import org.cc.service.UserService;
import org.cc.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author cc
 * @Date 2022/10/13 19:26
 * @PackageName:org.cc.service.imp
 * @ClassName: UserServiceImp
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao ;

    public User login(String username, String password) throws SQLException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        User user = userDao.login(map);
        System.out.println(userDao);
        return user;
//        return userDao.login(map);
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void register(Vip vip) throws RuntimeException {
        String password = vip.getPassword();
        vip.setPassword(Tools.md5(vip.getPassword()));//密码加密
        userDao.register(vip);
        if (password.trim().equals("")||vip.getUsername().trim().equals("")){
            throw new RuntimeException("用户名或密码为空！");
        }
    }



    public Vip QiantaiLogin(String username, String password) throws SQLException {

        password = Tools.md5(password);
        Map<String,String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        Vip vip = userDao.QiantaiLogin(map);
        return vip;
    }
}
