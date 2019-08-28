package com.igeekshop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.igeekshop.domain.User;
import com.igeekshop.service.IAdminProductService;
import com.igeekshop.service.impl.AdminProductServiceImpl;

/**
 * Servlet implementation class AdminIndexServlet
 */
@WebServlet("/AdminIndexServlet")
public class AdminIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码问题
        request.setCharacterEncoding("UTF-8");
        
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		IAdminProductService service = new AdminProductServiceImpl();
		User user = null;
		try {
			user = service.AdminLogin(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //设置Session
        HttpSession session = request.getSession();
//        System.out.println(user);
		if(user!=null&&user.getPassword().equals(password)) {
			session.setAttribute("adminuser", user);
			session.setMaxInactiveInterval(30 * 60);
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
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
