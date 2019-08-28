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

/**
 * Servlet implementation class delProductFromCart
 */
@WebServlet("/delProductFromCart")
public class delProductFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delProductFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //获得要删除的item的pid
        String pid = request.getParameter("pid");
        
        //删除session中的购物车中的购物项集合中的item
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if(cart!=null){
            Map<String, CartItem> cartItems = cart.getCartItems();
            //修改总价
            cart.setTotal(cart.getTotal()-cartItems.get(pid).getSubtotal());
            cartItems.remove(pid);
            cart.setCartItems(cartItems);
        }
        
        session.setAttribute("cart", cart);
        
        //跳转回购物车
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
