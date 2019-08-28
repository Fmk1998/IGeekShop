package com.igeekshop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igeekshop.domain.Category;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IAdminProductService;
import com.igeekshop.service.impl.AdminProductServiceImpl;

/**
 * Servlet implementation class AdminProductListServlet
 */
@WebServlet("/adminProductList")
public class AdminProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("utf-8");
		
		// 传递请求到service层
        IAdminProductService service = new AdminProductServiceImpl();
            //调用service层的方法
        List<Product> productList = null;
        try {
           productList =  service.findAllProduct();
       } catch (SQLException e) {
           
           e.printStackTrace();
       }
       
        List<Category> categoryList = null;
        try {
           categoryList =  service.findAllCategory();
       } catch (SQLException e) {
           
           e.printStackTrace();
       }
       // 将获取到的categoryList存储到request域中
        request.setAttribute("categoryList", categoryList);
       // 将productList放到request域
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
