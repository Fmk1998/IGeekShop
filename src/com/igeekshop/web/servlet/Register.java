package com.igeekshop.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igeekshop.common.utils.MyBeanUtils;
import com.igeekshop.domain.User;
import com.igeekshop.service.IUserService;
import com.igeekshop.service.impl.UserServiceImpl;

import com.igeekshop.common.utils.CommonUtils;
import com.igeekshop.common.utils.MailUtils;
/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		指定返回的格式和编码   格式是html的格式   编码是utf-8
		response.setContentType("text/html;charset=utf-8");
//		指定获取前台请求是的编码   是utf-8
		request.setCharacterEncoding("utf-8");
		
		//发送激活码
		String email = request.getParameter("email");
		String uuid = CommonUtils.getUUID();
		String emailMsg = "<a href=\"http://localhost:8080/IGeekShop/Active2?code="
				+ uuid+ "\" >欢迎注册,点击激活!</a>";
//		System.out.println(emailMsg);
		MailUtils.sendMail(email, emailMsg);
        
		//注册到数据库
		Map<String, String[]> properties = request.getParameterMap();
		User user = MyBeanUtils.populate(User.class, properties);
		user.setCode(uuid);
		System.out.println(user);
		IUserService userServiceImpl = new UserServiceImpl();
		boolean isSuccess = userServiceImpl.register(user);
		if (isSuccess) {
			response.getWriter().write("{\"status\":0}");
		}else {
			response.getWriter().write("{\"status\":1}");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
