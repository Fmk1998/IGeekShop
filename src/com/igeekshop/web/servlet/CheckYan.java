package com.igeekshop.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CheckYan
 */
@WebServlet("/CheckYan")
public class CheckYan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckYan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		指定返回的格式和编码   格式是json的格式   编码是utf-8
		response.setContentType("application/json; charset=utf-8");
//		指定获取前台请求是的编码   是utf-8
		request.setCharacterEncoding("utf-8");
		String yan = request.getParameter("yan");
		String code = (String) request.getSession().getAttribute("code");
//		response.getWriter().write(yan);
//		response.getWriter().write(code);
		if(!yan. toUpperCase().equals(code)) {
			response.getWriter().write("false");
			return;
		}else {
			response.getWriter().write("true");
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
