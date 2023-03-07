package org.cc.filter; /**
 * @Author cc
 * @Date 2022/10/14 19:05
 * @PackageName:${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Description: TODO
 * @Version 1.0
 */

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PermissionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse res = ((HttpServletResponse) response);
        HttpSession session = req.getSession();
        if (session.getAttribute("user")==null){
            //用户未登录
            res.sendRedirect("index.jsp");
            return;
        }
        chain.doFilter(request, response);
    }
}
