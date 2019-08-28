package com.igeekshop.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.igeekshop.domain.Cart;
import com.igeekshop.domain.CartItem;
import com.igeekshop.domain.Order;
import com.igeekshop.domain.OrderItem;
import com.igeekshop.domain.User;
import com.igeekshop.service.IOrderService;
import com.igeekshop.service.impl.OrderServiceImpl;
import com.igeekshop.common.utils.CommonUtils;

/**
 * Servlet implementation class submitOrder
 */
@WebServlet("/submitOrder")
public class submitOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submitOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    
		//首先需要判断用户是否登录
	    User user = (User) session.getAttribute("user");
	    if(user==null){
	        //如果用户没有登录的话，就重定向到登录页面
	        response.sendRedirect(request.getContextPath()+"/login.jsp");
	        //不在执行继续执行之后的代码
	        return;
	    }
		
	    //目的：封装好一个Order对象 传递给service层
	    Order order = new Order();
	    //该订单的编号
	    String oid = CommonUtils.getUUID();
	    order.setOid(oid);
	    // 2 private Date ordertime ; //下单时间
	    order.setOrdertime(new Date());
	    
	    // 3 private double total; //该订单的总金额
	      //首先应该获得session中的购物车Cart对象
	    Cart cart = (Cart) session.getAttribute("cart");
	    double total = 0.0;
	    if(cart!=null){
	        total = cart.getTotal();
	    }else {
	    	response.sendRedirect(request.getContextPath()+"/cart.jsp");
	    	return;
	    }
	    order.setTotal(total);
	    
	    // 4 private int state ; //该订单的支付状态 1代表已付款 0代表未付款
	    order.setState(0);
	    
	    // 5 private String address; //收获地址
	    order.setAddress(null);
	    // 6 private String name; //姓名
	    order.setName(null);
	    // 7 private String telephone; //电话
	    order.setTelephone(null);
	    
	    // 8 private User user;//该订单属于哪个用户
	    order.setUser(user);
	    
	    // 9 该订单中有多少订单项
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
	        
	        //将该订单项添加到订单的订单项集合中
	        order.getOrderItems().add(orderItem);
	    }
	    
	     //order对象封装完毕
	    //传递数据到service层
	    IOrderService orderserviceImpl = new OrderServiceImpl();
	    orderserviceImpl.insertOrder(order);
	    
	    session.setAttribute("order", order);
	    //页面跳转
	    response.sendRedirect(request.getContextPath()+"/order_info.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
