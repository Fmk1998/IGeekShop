package com.igeekshop.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.igeekshop.domain.Category;
import com.igeekshop.domain.Condition;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IAdminProductService;
import com.igeekshop.service.impl.AdminProductServiceImpl;

/**
 * Servlet implementation class AdminSearchProductListServlet
 */
@WebServlet("/adminSearchProductList")
public class AdminSearchProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchProductListServlet() {
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
		// 1 获取用户输入的查询条件数据
        Map<String, String[]> properties = request.getParameterMap();
        
        // 2 封装获取到的数据到condition对象中
        Condition condition = new Condition();
          //利用BeauUtils进行子映射封装
          //将散装的查询数据封装到一个 VO 实体中
        try {
			BeanUtils.populate(condition, properties);
		} catch (IllegalAccessException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
           //到这里condition对象已经封装好了
        // 3 将获取到的数据传递给service层
        IAdminProductService service = new AdminProductServiceImpl();
        List<Product> productList = null;
        try {
            productList =  service.findProductListByCondition(condition);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
        //点击搜索之后，信息回显实现
        //获得所有的商品的类别数据
        List<Category> categoryList = null;
        try {
            categoryList =  service.findAllCategory();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        // 4 将数据存储到request域中
        request.setAttribute("productList", productList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("condition", condition);//用于数据的回显判断依据
          //转发
        request.getRequestDispatcher("admin/product/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
