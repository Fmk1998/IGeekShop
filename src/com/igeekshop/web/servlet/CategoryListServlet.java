package com.igeekshop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.igeekshop.domain.Category;
import com.igeekshop.service.IProductService;
import com.igeekshop.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class CategoryListServlet
 */
@WebServlet("/CategoryListServlet")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryListServlet() {
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
		IProductService productServiceImpl = new ProductServiceImpl();
		 // 准备分类数据
        List<Category> categoryList = null;
        try {
            categoryList = productServiceImpl.findAllCategory();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        //使用转换工具将categoryList转换成json格式
        Gson gson = new Gson();
        String json = gson.toJson(categoryList);
        
        //将转换后的json格式字符串写出
          //写回前先解决乱码问题
        response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
