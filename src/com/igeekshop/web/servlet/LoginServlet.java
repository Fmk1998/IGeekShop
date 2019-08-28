package com.igeekshop.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.igeekshop.domain.User;
import com.igeekshop.service.IUserService;
import com.igeekshop.service.impl.UserServiceImpl;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberName = request.getParameter("rememberName");
		String autoLogin = request.getParameter("autoLogin");
		
		IUserService userServiceImpl = new UserServiceImpl();
		User user = userServiceImpl.login(username);
		System.out.println(user);
		int state = user.getState();
		if (state == 0) {
			response.getWriter().write("{\"status\":1}");
			return;
		}
		
		if(user.getPassword().equals(password)) {
			response.getWriter().write("{\"status\":0}");
		}else {
			response.getWriter().write("{\"status\":2}");
			return;
		}
		
		//设置Cookies
		Cookie cookie = new Cookie("Cookie_username",username);
		if(rememberName!=null) {
			// 不为空勾选了 ==>  设置有效时间为一小时
            cookie.setMaxAge(60*60);
		}else {
			// null 没勾选 ==>  设置cookie的有效时间为0
            cookie.setMaxAge(0);
		}
		//将cookie添加到response
        response.addCookie(cookie);
        
        if(autoLogin!=null) {
        	Cookie cookie_username = new Cookie("cookie_username", user.getUsername());
            Cookie cookie_password = new Cookie("cookie_password", user.getPassword());
         // 设置 cookie 的持久化时间
            cookie_username.setMaxAge(60 * 60);
            cookie_password.setMaxAge(60 * 60);
         // 发送 cookie
            response.addCookie(cookie_username);
            response.addCookie(cookie_password);
        }
        
        //设置Session
        HttpSession session = request.getSession();
		session.setAttribute("user", user);
		session.setMaxInactiveInterval(30 * 60);
//		session.setAttribute("uid", user.getUid());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
