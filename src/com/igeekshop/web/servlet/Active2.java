package com.igeekshop.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igeekshop.domain.User;
import com.igeekshop.service.IUserService;
import com.igeekshop.service.impl.UserServiceImpl;

/**
 * Servlet implementation class Active2
 */
@WebServlet("/Active2")
public class Active2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Active2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		指定返回的格式和编码   格式是html的格式   编码是utf-8
		response.setContentType("text/html;charset=utf-8");
//		指定获取前台请求是的编码   是utf-8
		request.setCharacterEncoding("utf-8");
		
		String code = request.getParameter("code");
//		数据库修改用户的激活状态  根据code去修改
		IUserService userServiceImpl = new UserServiceImpl();
		User user = userServiceImpl.jihuo(code);
		if (user == null) {
			response.getWriter().write("false");
			return;
		}else {
			user.setState(1);
			boolean isSuccess = userServiceImpl.gengxin(user);
			if (isSuccess) {
				response.getWriter().write("success");
			}else {
				response.getWriter().write("false");
			}
		}
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
