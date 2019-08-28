package com.igeekshop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igeekshop.domain.Page;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IProductService;
import com.igeekshop.service.impl.ProductServiceImpl;



/**
 * Servlet implementation class SelectProduct
 */
@WebServlet("/SelectProduct")
public class SelectProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPageStr = request.getParameter("currentPage");
        if(currentPageStr == null){
            currentPageStr = "1";
        }
        int currentPage = Integer.parseInt(currentPageStr);
        int currentCount =12;
        String cid = request.getParameter("cid");
        IProductService productServiceImpl = new ProductServiceImpl();
        Page<Product> pageBean = productServiceImpl.findProductListByCid(cid,currentPage,currentCount);
        
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("cid", cid);
		
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
                        product = productServiceImpl.selectProductByPid(pid);
						historyProductList.add(product);
                    }
                }
                
            }
        }
        //将历史记录的集合放到域中
        request.setAttribute("historyProductList", historyProductList);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
