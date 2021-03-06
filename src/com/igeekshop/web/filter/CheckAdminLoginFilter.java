package com.igeekshop.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.igeekshop.domain.User;

/**
 * Servlet Filter implementation class CheckAdminLoginFilter
 */
@WebFilter(filterName = "/CheckAdminLoginFilter",
urlPatterns={"/AdminAddCategoryServlet","/AdminAddProductServlet","/AdminAddProductUIServlet","/adminCategoryList","/delCategory","/delProduct","/AdminEditCategorySaveServlet","/AdminEditCategoryServlet","/adminEditProductSave","/adminEditProduct","/AdminOrderListServlet","/adminProductList","/adminSearchProductList","/admin/home.jsp","/admin/order/*","/admin/product/*"}
           )
public class CheckAdminLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckAdminLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//防止乱码
				request.setCharacterEncoding("UTF-8");
		        response.setCharacterEncoding("UTF-8");
				HttpServletRequest req = (HttpServletRequest) request;
		        HttpServletResponse resp = (HttpServletResponse) response;
		      //校验用户是否登录----校验session是否存在user对象
		        HttpSession session = req.getSession();
		      //判断用户是否已经登录 未登录下面代码不执行
		        User user = (User) session.getAttribute("adminuser");
		        if(user==null){
		            //没有登录
		            resp.sendRedirect(req.getContextPath()+"/admin/index.jsp");
		            return;
		        }
		        
		        chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
