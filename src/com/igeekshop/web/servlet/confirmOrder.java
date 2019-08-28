package com.igeekshop.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.igeekshop.domain.Cart;
import com.igeekshop.domain.CartItem;
import com.igeekshop.domain.Order;
import com.igeekshop.domain.OrderItem;
import com.igeekshop.service.IOrderService;
import com.igeekshop.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class confirmOrder
 */
@WebServlet("/confirmOrder")
public class confirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		 //1、更新收货人信息
		Order order = new Order();
	    Map<String, String[]> properties = request.getParameterMap();
	    try {
			BeanUtils.populate(order, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    IOrderService orderserviceImpl = new OrderServiceImpl();
	    orderserviceImpl.updateOrderAdrr(order);
   
	    Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
        	response.sendRedirect(request.getContextPath()+"/cart.jsp");
	    	return;
	    }
	    //从购物车中获取所有的cartItem
	    Map<String, CartItem> cartItems = cart.getCartItems();
	    //遍历所有的购物车项，加入到订单项orderItem中
	    for(Map.Entry<String, CartItem> entry:cartItems.entrySet()){
	        //取出里面的每一个购物项
	        CartItem cartItem = entry.getValue();
	        
	        // 创建新的订单项
	        OrderItem orderItem = new OrderItem();
	        
	        //订单项内商品的购买数量
	        orderItem.setCount(cartItem.getBuyNum());
	         //订单项小计
	        orderItem.setSubtotal(cartItem.getSubtotal());
	        //订单项内的商品
	        orderItem.setProduct(cartItem.getProduct());
	        //该订单项属于哪个订单
	        orderItem.setOrder(order);
	        
	        //存入数据库
	        orderserviceImpl.insertOrderItem(orderItem);
	    }
	    
	    session.removeAttribute("cart");
		response.sendRedirect(request.getContextPath()+"/SelectOrderList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
