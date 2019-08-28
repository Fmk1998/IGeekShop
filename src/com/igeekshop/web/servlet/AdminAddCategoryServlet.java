package com.igeekshop.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.igeekshop.domain.Category;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IAdminProductService;
import com.igeekshop.service.impl.AdminProductServiceImpl;

/**
 * Servlet implementation class AdminAddCategoryServlet
 */
@WebServlet("/AdminAddCategoryServlet")
public class AdminAddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddCategoryServlet() {
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
        String cname = request.getParameter("cname");
        Category  category = new Category();
        category.setCname(cname);
        //上架日期
        IAdminProductService service = new AdminProductServiceImpl();
        boolean isSuccess = true;
        try {
            isSuccess = service.addCategory(cname);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
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
