package com.igeekshop.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.igeekshop.domain.Order;
import com.igeekshop.domain.OrderItem;
import com.igeekshop.domain.Product;
import com.igeekshop.domain.User;
import com.igeekshop.service.IOrderService;
import com.igeekshop.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class SelectOrderList
 */
@WebServlet("/SelectOrderList")
public class SelectOrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOrderList() {
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
		//获取session
		HttpSession session = request.getSession(true);
		// 获取session传过来的值
		User user=(User) session.getAttribute("user");
		int uid = user.getUid();
		IOrderService orderServiceImpl = new OrderServiceImpl();
		List<Order> orders = orderServiceImpl.selectOrderByUid(uid);
		if(orders!=null) {
			 for (Order order : orders) {
		            try {
		            	//根据order的oid进行查询
			            //获得每一个订单的oid
			            String oid = order.getOid();
			            //查询该订单的所有订单项 ---mapList封装的是多个订单项和该订单项中的商品的信息。
			            List<Map<String, Object>> mapList = orderServiceImpl.findAllItemsByOid(oid);
			            //将mapList转换成List<OrderItem> orderItems
			            for(Map<String, Object> map:mapList){
			            	//1  从map中取出count subtotal 封装到OrderItem中
		                    //item.setCount(Integer.parseInt(map.get("count").toString()));
		                    OrderItem item = new OrderItem();
		                    BeanUtils.populate(item, map);
		                    
		                    // 2 从map中取出pimage pname shop_price 封装到product中
		                    Product product = new Product();
		                    BeanUtils.populate(product, map);
		                    
		                    // 3 将product 封装到OrderItem中
		                    item.setProduct(product);
		                    
		                    // 4  将OrderItem封装到List<OrderItem> orderItems中
		                    order.getOrderItems().add(item);
			            }
					} catch (Exception e) {
						// TODO: handle exception
					}
			 }
		}
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/order_list.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
