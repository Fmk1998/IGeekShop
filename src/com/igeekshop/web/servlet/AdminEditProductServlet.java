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
 * Servlet implementation class AdminEditProductServlet
 */
@WebServlet("/adminEditProduct")
public class AdminEditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取数据
        String pid = request.getParameter("pid");
//        System.out.println(pid);
        // 传递数据到service层
        IAdminProductService service = new AdminProductServiceImpl();
        Product product = null;
        try {
            product = service.editProduct(pid);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
//        System.out.println(product);
        // 将数据传递给request域
        request.setAttribute("product", product);
        
      //获得所有的商品的类别数据
        List<Category> categoryList = null;
        try {
           categoryList =  service.findAllCategory();
       } catch (SQLException e) {
           
           e.printStackTrace();
       }
//        System.out.println(categoryList);
       // 将获取到的categoryList存储到request域中
       request.setAttribute("categoryList", categoryList);

       request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
