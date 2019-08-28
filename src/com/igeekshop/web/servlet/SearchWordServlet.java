package com.igeekshop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IProductService;
import com.igeekshop.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class SearchWordServlet
 */
@WebServlet("/SearchWordServlet")
public class SearchWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchWordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得关键字
        String word = request.getParameter("word");
        Gson gson = new Gson();
        String json = "";
        if(word!=""&&word!=null){
        	//查询该关键字的所有商品
            IProductService service = new ProductServiceImpl();
            List<Object> productList = null;
            try {
                productList = service.findProductByWord(word);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            //["xiaomi","huawei",""...]
            
            //使用json的转换工具将对象或集合转成json格式的字符串
            /*JSONArray fromObject = JSONArray.fromObject(productList);
            String string = fromObject.toString();
            System.out.println(string);*/
            
            json = gson.toJson(productList);
            System.out.println(json);
        }else {
        	json ="";
        }
        
        
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
