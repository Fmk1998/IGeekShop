package com.igeekshop.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.igeekshop.common.utils.CommonUtils;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IAdminProductService;
import com.igeekshop.service.impl.AdminProductServiceImpl;

/**
 * Servlet implementation class AdminAddProductServlet
 */
@WebServlet("/AdminAddProductServlet")
public class AdminAddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//目的：收集表单的数据封装一个Product实体 将上传图片存储到服务器磁盘上
        Product product = new Product();
        //收集数据的容器
        Map<String,Object> map = new HashMap<String,Object>();

        
        try {
            //接收文件上传
            // 1 创建磁盘文件项工厂
            String path_temp = this.getServletContext().getRealPath("temp");
            DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new File(path_temp));
            
            // 2 创建文件上传核心类
            ServletFileUpload upload = new ServletFileUpload(factory);
              //设置上传文件的名称的编码
            upload.setHeaderEncoding("UTF-8");
            
            //ServletFileUpload的API
            boolean multipartContent = ServletFileUpload.isMultipartContent(request); //判断表单是不是文件上传的表单
            if(multipartContent){ //是文件上传表单
                // 3 ******解析request----  获得文件项集合
                List<FileItem> parseRequest = upload.parseRequest(request);
                if(parseRequest!=null){
                    // 4 遍历文件项集合
                    for (FileItem fileItem : parseRequest) {
                        // 5 判断是普通表单项/文件上传项
                        boolean formField = fileItem.isFormField();
                        if(formField){ 
                            //普通表单项 username = zhangsan
                            String fieldName = fileItem.getFieldName();
                            String fieldValue = fileItem.getString("UTF-8");//对普通表单项的内容进行编码
                            
                            //一个一个的存入到容器中 键值对  存储完成之后使用BeanUtils进行封装。
                            map.put(fieldName, fieldValue);
                            
                            // 注意：当表单是enctype="multipart/form-data"时 request.getParameter相关的方法都失效
                            //String parameter = request.getParameter("username");获取不到数据
                            
                        }else{
                            // 文件上传项
                            String fileName = fileItem.getName();//获取文件名
                            //不同的浏览器提交的文件名是不一样的，有些浏览器提交的文件名是带有路径的，
                            //如：c:\nihao\a.txt，而有些只是单纯的文件名，如：a.txt  
                            //处理获取到的上传文件的文件名的路径部分，只保留文件名部分 
                            fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                            
                            
                            //获得上传文件的内容  也是就获得与文件关联的输入流
                            InputStream in = fileItem.getInputStream();
                                //获取存储文件的绝对地址
                            String path_store = this.getServletContext().getRealPath("upload");
                            OutputStream out = new FileOutputStream(path_store+"/"+fileName); //D:/xxx/xxx/xx/xxx.jpg
                                //上传文件实际上就是复制文件到服务器 直接使用工具类
                            IOUtils.copy(in, out);
                                //关闭资源
                            in.close();
                            out.close();
                            //删除临时文件
                            fileItem.delete();
                           
                            //将图片的相对地址存储到map容器中，这样就可以直接使用BeanUtils封装到里面去了
                            map.put("pimage", "upload/"+fileName);
                        }
                        
                    }
                    
                    
                }
                //封装
                BeanUtils.populate(product, map);
                //是否product对象封装数据完全,不完全的手动封装
                
                // private Date pdate;
                product.setPdate(new Date());
                
                // private int pflag;
                product.setPflag(0);
                product.setCid(map.get("cid").toString());
                
                //将封好的product传递给service层
                IAdminProductService service = new AdminProductServiceImpl();
                boolean isSuccess = true;
                try {
                    isSuccess = service.addProduct(product);
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
                System.out.println(product);
                //将处理结果存储到request域中
                request.setAttribute("isSuccess", isSuccess);
                 //跳转到列表页面
                //response.sendRedirect(request.getContextPath()+"/adminProductList");
                request.getRequestDispatcher("adminProductList").forward(request, response);
                
            }else{
                //不是文件上传表单
                //使用原始的表单数据的获取方式
            }
        } catch (Exception e) {
            // TODO: handle exception
        	System.out.println("a");
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
