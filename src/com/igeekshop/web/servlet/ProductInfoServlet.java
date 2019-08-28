package com.igeekshop.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igeekshop.domain.Category;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IProductService;
import com.igeekshop.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ProductInfoServlet
 */
@WebServlet("/ProductInfoServlet")
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取pid
		String pid = request.getParameter("pid");
        //传递给service层并调取service层的方法
        IProductService productServiceImpl = new ProductServiceImpl();
        Product product = productServiceImpl.selectProductByPid(pid);
        String cid = product.getCid();
        Category category = productServiceImpl.selectCategoryByCid(cid);
        product.setCategory(category);
        //存储到request域中
        request.setAttribute("product", product);
        String pids = null;
        //设置Cookie
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
        	for(Cookie cookie:cookies){
        		if("pids".equals(cookie.getName())){
        			 pids = cookie.getValue(); 
        			 String[] split = pids.split("-");
        			 List<String> asList = Arrays.asList(split);
                     LinkedList<String> list = new LinkedList<String>(asList);
                     
                     if(list.contains(pid)){
                         //包含当前查看的商品的pid
                         list.remove(pid);
                     }
                     list.addFirst(pid);
                     
                   //将[3,1,2]转成3-1-2字符串
                     StringBuffer sb = new StringBuffer();
                     for(int i=0;i<list.size()&&i<7;++i){ //&&i<7 页面最多显示7个浏览商品的历史记录
                         sb.append(list.get(i));
                         sb.append("-"); //3-1-2-
                     }
                     //去掉3-1-2-后面的-   
                     //substring包含头不包含尾
                     pids = sb.substring(0, sb.length()-1);
        		}
        	}
        }
        
      //创建cookie回写
        Cookie cookie_pids = new Cookie("pids", pids);
        cookie_pids.setMaxAge(60*60); //单位为秒  设置cookie的存储事件一个小时
        //设置cookie的携带路径
        cookie_pids.setPath(request.getContextPath());
        //将cookie_pids写回去
        response.addCookie(cookie_pids);
        
          //转发
        request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
