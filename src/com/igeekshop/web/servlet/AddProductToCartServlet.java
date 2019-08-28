package com.igeekshop.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.igeekshop.domain.Cart;
import com.igeekshop.domain.CartItem;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IProductService;
import com.igeekshop.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class addProductToCart
 */
@WebServlet("/addProductToCart")
public class AddProductToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    IProductService productserviceImpl = new ProductServiceImpl();
	        HttpSession session = request.getSession(true);
	        
	        //获得要放到购物车的商品的pid
	        String pid = request.getParameter("pid");
	        
	        //获得该商品的购买数量
	        int buyNum = Integer.parseInt(request.getParameter("buyNum")) ;
	        
	        //获得product对象
	        Product  product = null;
	        product = productserviceImpl.selectProductByPid(pid);
	        
	        //计算小计
	        double subtotal = product.getShop_price()*buyNum;
	        
	        //封装cartItem
	        CartItem item = new CartItem();
	        item.setProduct(product);
	        item.setBuyNum(buyNum);
	        item.setSubtotal(subtotal);
	        
	        //获得购物车---判断是否在session中已经存在购物车
	        Cart cart = (Cart) session.getAttribute("cart");
	        if(cart==null){
	            cart = new Cart();
	        }
	        
	        // 将购物项放到车中----key是pid
	        //先判断购物车中是否已经包含此购物项目------判断key是否已经存在
	        //如果购物车已经存在该商品-----将现在买的数量与原有的数进行相加操作
	        Map<String, CartItem> cartItems = cart.getCartItems();
	        double newsubtotal = 0.0;
	        if(cartItems.containsKey(pid)){
	            //取出原有的商品的数量
	            CartItem cartItem = cartItems.get(pid);
	            int oldBuyNum = cartItem.getBuyNum();
	            //修改后的数量
	            int newBuyNum = oldBuyNum + buyNum;
	            cartItem.setBuyNum(newBuyNum);
	            
	            
	            //修改小计
	            //原来该商品的小计
	            double oldsubtotal = cartItem.getSubtotal();
	            //新买的该商品的小计
	            newsubtotal = buyNum*product.getShop_price();
	            cartItem.setSubtotal(newsubtotal+oldsubtotal);
	            
	            //修改之后重新存入
	            cart.setCartItems(cartItems);
	            
	        }else{
	            //如果购物车中没有该商品
	        	String pids = product.getPid();
	            cart.getCartItems().put(pids, item);
	            newsubtotal = buyNum*product.getShop_price();
	        }
	        //计算总计
	        double total = cart.getTotal()+newsubtotal;
	        cart.setTotal(total);
	        
	        // 将车再次放入session
	        session.setAttribute("cart", cart);
	        
	        //转发 直接跳转到购物车页面  转发的话，每次刷新金额都不变化不可以
	        //request.getRequestDispatcher("/cart.jsp").forward(request, response);
	        
	        response.sendRedirect(request.getContextPath()+"/cart.jsp");
	        
	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
