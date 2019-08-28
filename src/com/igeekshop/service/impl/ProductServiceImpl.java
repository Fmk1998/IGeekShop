package com.igeekshop.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.igeekshop.dao.IProductDao;
import com.igeekshop.dao.impl.ProductDaoImpl;
import com.igeekshop.domain.Category;
import com.igeekshop.domain.Page;
import com.igeekshop.domain.Product;
import com.igeekshop.service.IProductService;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public class ProductServiceImpl implements IProductService{
	
	private IProductDao productDaoImpl = new ProductDaoImpl();
	
	@Override
	public List<Product> selectAllProduct() {
		return productDaoImpl.selectAllProduct();
	}


	@Override
	public boolean addProduct(Product product) {
		return productDaoImpl.insertProduct(product)>0;
	}

	@Override
	public boolean updateProduct(Product product) {
		return productDaoImpl.updateProduct(product)>0;
	}

	@Override
	public List<Product> selectAllProductByCid(String cid) {
		return productDaoImpl.selectProductByCid(cid);
	}

	@Override
	public Product selectProductByName(String pname) {
		return productDaoImpl.selectProductByName(pname);
	}

	@Override
	public Product selectProductByPid(String pid) {
		return productDaoImpl.selectProductByPid(pid);
	}


	@Override
	public List<Product> findHotProductList() throws SQLException {
        return productDaoImpl.findHotProductList();
	}


	@Override
	public List<Product> findNewProductList() throws SQLException {
		return productDaoImpl.findNewProductList();
	}


	@Override
	public List<Category> findAllCategory() throws SQLException {
		// TODO Auto-generated method stub
		return productDaoImpl.findAllCategory();
	}


	@Override
	public Category selectCategoryByCid(String cid) {
		// TODO Auto-generated method stub
		return productDaoImpl.selectCategoryByCid(cid);
	}


	@Override
	public Page findPageBean(int currentPage, int currentCount) throws SQLException {
		//目的：就是想办法封装一个PageBean 并返回
        Page pageBean = new Page();
        //1、当前页private int currentPage;
        pageBean.setCurrentPage(currentPage);
        //2、当前页显示的条数private int currentCount;
        pageBean.setCurrentCount(currentCount);
        //3、总条数private int totalCount;
        int totalCount = productDaoImpl.getTotalCount();
        pageBean.setTotalCount(totalCount);
        //4、总页数private int totalPage;
        /*
         * 总条数        当前页显示的条数    总页数
         * 10        4                3
         * 11        4                3
         * 12        4                3
         * 13        4                4
         * 
         * 公式：总页数=Math.ceil(总条数/当前显示的条数)
         * 
         */
        int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
        pageBean.setTotalPage(totalPage);
        //5、每页显示的数据private List<T> productList = new ArrayList<T>();
        /*
         * 页数与limit起始索引的关系
         * 例如 每页显示4条
         * 页数        其实索引        每页显示条数
         * 1        0            4
         * 2        4            4
         * 3        8            4
         * 4        12            4
         * 
         * 索引index = (当前页数-1)*每页显示的条数
         * 
         */
        int index = (currentPage-1)*currentCount;
        
        List<Product> productList = productDaoImpl.findProductListForPageBean(index,currentCount);
        pageBean.setProductList(productList);
        
        return pageBean;
	}


	@Override
	public Page<Product> findProductListByCid(String cid, int currentPage, int currentCount) {
		//封装一个pageBean并返回给web层
        Page<Product> pageBean = new Page<Product>();
        // 1 封装当前页
        pageBean.setCurrentPage(currentPage);
        
        //2 封装每页显示的条数
        pageBean.setCurrentCount(currentCount);
        
        //3 封装总条数
        int totalCount = 0;
        try {
            totalCount = productDaoImpl.getTotalCount(cid);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        pageBean.setTotalCount(totalCount);
        
        //4 封装总页数
        int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
        pageBean.setTotalPage(totalPage);
        
        //5 当前页显示的数据
        List<Product> list = null;
        int index = (currentPage-1)*currentCount;
        try {
            list = productDaoImpl.findProductListByPage(cid,index,currentCount);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        pageBean.setList(list);
        
        
        return pageBean;
	}

	@Override
	public List<Object> findProductByWord(String word) throws SQLException {
		return productDaoImpl.findProductByWord(word);
	}


	@Override
	public List<Product> findProductListByName(String search) throws SQLException {
		return productDaoImpl.findProductListByName(search);
	}





}
