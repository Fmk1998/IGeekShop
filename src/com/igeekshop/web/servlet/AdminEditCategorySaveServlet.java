package com.igeekshop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igeekshop.domain.Category;
import com.igeekshop.service.IAdminProductService;
import com.igeekshop.service.impl.AdminProductServiceImpl;

/**
 * Servlet implementation class AdminEditCategorySaveServlet
 */
@WebServlet("/AdminEditCategorySaveServlet")
public class AdminEditCategorySaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditCategorySaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决乱码问题
        request.setCharacterEncoding("UTF-8");
        //获取数据
        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        Category category = new Category();
        category.setCid(cid);
        category.setCname(cname);
        IAdminProductService service = new AdminProductServiceImpl();
        boolean isSuccess = true;
        try {
            isSuccess = service.editCategorySave(category);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        System.out.println(cid);
        System.out.println(cname);
        System.out.println(isSuccess);
        //将处理结果存储到request域中
        request.setAttribute("isSuccess", isSuccess);
         //跳转到列表页面
        //response.sendRedirect(request.getContextPath()+"/adminProductList");
        request.getRequestDispatcher("/adminCategoryList").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
