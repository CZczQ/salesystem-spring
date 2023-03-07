package org.cc.filter; /**
 * @Author cc
 * @Date 2022/10/15 15:19
 * @PackageName:${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Description: TODO
 * @Version 1.0
 */

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        chain.doFilter(request, response);
    }
}
