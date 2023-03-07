package org.cc.web;

import org.cc.pojo.User;
import org.cc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @Author cc
 * @Date 2022/12/17 10:44
 * @PackageName:org.cc.web
 * @ClassName: UserController
 * @Description: TODO
 * @Version 1.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/houtai/usersvl")
    public String login(String user, String pwd, Model model, HttpServletRequest request, HttpServletResponse response){
        //合法性判断
        if (user == null || user.trim().equals("") || pwd == null || pwd.trim().equals("")){
            model.addAttribute("errinfo","用户名及密码不能为空");
            return "forward:/houtai/index.jsp";
        }
        //调用业务逻辑，检查用户的密码
        User user1 = null;
        try {
            user1 = userService.login(user, pwd);
            if (user1==null){
                model.addAttribute("errinfo","用户名及密码不能为空");
                return "forward:/houtai/index.jsp";
            }else {//用户名密码正确
                request.getSession().setAttribute("user",user1);
                //把用户名及密码写到Cookie中
                Cookie ck1 = new Cookie("username",user1.getUsername());
                Cookie ck2 = new Cookie("password",user1.getPassword());
                //有效期为一天
                ck1.setMaxAge(60*60*24);
                ck2.setMaxAge(60*60*24);
                response.addCookie(ck1);
                response.addCookie(ck2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/houtai/main.html";
    }
}
