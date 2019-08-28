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
import com.igeekshop.service.IProductService;
import com.igeekshop.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class indexServlet
 */
@WebServlet("/indexServlet")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        IProductService productServiceImpl = new ProductServiceImpl();
        
        //获取热门商品-----List<Product>
        List<Product> hotProductList = null;
        try {
            hotProductList = productServiceImpl.findHotProductList();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
        //获取最新商品-----List<Product>
        List<Product> newProductList = null;
        try {
            newProductList = productServiceImpl.findNewProductList();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
        //准备分类数据
        List<Category> categoryList =null;
        try {
            categoryList = productServiceImpl.findAllCategory();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        request.setAttribute("categoryList", categoryList);
        
        
        //将获取的数据存入request域
        request.setAttribute("hotProductList", hotProductList);
        request.setAttribute("newProductList", newProductList);
        
        //转发
        request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
