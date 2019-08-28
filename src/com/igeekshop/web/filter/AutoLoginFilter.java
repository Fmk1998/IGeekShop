package com.igeekshop.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.igeekshop.domain.User;
import com.igeekshop.service.IUserService;
import com.igeekshop.service.impl.UserServiceImpl;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter(filterName = "/AutoLoginFilter",
		urlPatterns={"/indexServlet","/index.jsp","/login.jsp","/LoginServlet"})
public class AutoLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        // 获得 cookie 中用户名和密码 进行登录的操作
        // 定义 cookie_username
        String cookie_username = null;
        // 定义 cookie_password
        String cookie_password = null;
        // 获得 cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {// 获得名字是 cookie_username 和
                                            // cookie_password
                if ("cookie_username".equals(cookie.getName())) {
                    cookie_username = cookie.getValue();
                }
                if ("cookie_password".equals(cookie.getName())) {
                    cookie_password = cookie.getValue();
                }
            }
        }
        // 判断 username 和 password 是否是 null
        if (cookie_username != null && cookie_password != null && cookie_username != "" && cookie_password != "") {
            // 登录的代码
            IUserService service = new UserServiceImpl();
            
            User user = null;
            user = service.login(cookie_username);
            if(user.getPassword().equals(cookie_password)) {
                // 将登录的用户的 user 对象存到 session 中
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30 * 60);
            }else {
            	return;
            }
        }
         //放行
        chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
