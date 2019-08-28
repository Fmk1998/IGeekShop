package com.igeekshop.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.igeekshop.domain.Category;
import com.igeekshop.domain.Condition;
import com.igeekshop.domain.Page;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IAdminProductService;
import com.igeekshop.service.IProductService;
import com.igeekshop.service.impl.AdminProductServiceImpl;
import com.igeekshop.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class SearchProductList
 */
@WebServlet("/SearchProductList")
public class SearchProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductList() {
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
        String searchname = request.getParameter("searchname");
      
        
        // 3 将获取到的数据传递给service层
        IProductService service = new ProductServiceImpl();
        List<Product> productList = null;
		try {
			productList = service.findProductListByName(searchname);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        request.setAttribute("searchname", productList);
        
      //定义一个集合记录历史商品信息的集合
        List<Product> historyProductList = new ArrayList<Product>();      
        //获取客户端携带的名字叫pids的cookie
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if("pids".equals(cookie.getName())){
                    String pids = cookie.getValue(); //3-1-2
                    String[] split = pids.split("-");
                    for(String pid:split){
                        Product product =null;
                        product = service.selectProductByPid(pid);
						historyProductList.add(product);
                    }
                }
                
            }
        }
        //将历史记录的集合放到域中
        request.setAttribute("historyProductList", historyProductList);
        
        
          //转发
        System.out.println(productList);
        request.getRequestDispatcher("/search_list.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
