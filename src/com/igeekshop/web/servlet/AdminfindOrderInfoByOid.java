package com.igeekshop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.igeekshop.service.IAdminProductService;
import com.igeekshop.service.impl.AdminProductServiceImpl;

/**
 * Servlet implementation class AdminfindOrderInfoByOid
 */
@WebServlet("/AdminfindOrderInfoByOid")
public class AdminfindOrderInfoByOid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminfindOrderInfoByOid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//模拟从服务器读数据有一个延迟的效果
	    try {
	        Thread.sleep(5000);
	    } catch (InterruptedException e1) {
	        
	        e1.printStackTrace();
	    }
	    
	    //获得oid
	    String oid = request.getParameter("oid");
	    
		IAdminProductService service = new AdminProductServiceImpl();
	    List<Map<String,Object>> mapList = null;
	    try {
	        mapList = service.findOrderInfoByOid(oid);
	    } catch (SQLException e) {
	        
	        e.printStackTrace();
	    }
	    //格式转换
	    Gson gson = new Gson();
	    String json = gson.toJson(mapList);
	    //注意ajax提交回写
	    System.out.println(json);

	    response.setContentType("text/html;charset=UTF-8");
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
