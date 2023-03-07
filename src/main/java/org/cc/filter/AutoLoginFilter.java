package org.cc.filter; /**
 * @Author cc
 * @Date 2022/10/14 19:13
 * @PackageName:${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Description: TODO
 * @Version 1.0
 */

import org.cc.pojo.User;
import org.cc.service.UserService;
import org.cc.service.imp.UserServiceImp;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AutoLoginFilter implements Filter {

    private UserService userService = new UserServiceImp();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse res = ((HttpServletResponse) response);
        String username = null;
        String password = null;
        Cookie[] cks = req.getCookies();
        if(cks != null) {
            for (Cookie ck : cks) {
                if (ck.getName().equals("username")) {
                    username = ck.getValue();

                } else if (ck.getName().equals("password")) {
                    password = ck.getValue();
                }
            }
        }
        if (username == null || password == null){
            chain.doFilter(request, response);
        }else {
            try {
                User user = userService.login(username, password);
                if (user != null){
                    res.sendRedirect("main.html");
                }else{
                    chain.doFilter(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
}
