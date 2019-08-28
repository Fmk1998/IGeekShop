package com.igeekshop.service;

import java.sql.SQLException;
import java.util.List;

import com.igeekshop.domain.Category;
import com.igeekshop.domain.Page;
import com.igeekshop.domain.Product;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public interface IProductService {
	List<Product> selectAllProduct();
	List<Product> selectAllProductByCid(String cid);
	Product selectProductByName(String pname);
	Product selectProductByPid(String pid);
	boolean addProduct(Product product);
	boolean updateProduct(Product product);
	// 获取热门商品
	List<Product> findHotProductList() throws SQLException;
	// 获取最新商品商品
    List<Product> findNewProductList() throws SQLException;
    //获取商品分类
    List<Category> findAllCategory() throws SQLException;
    //分页操作
    Page findPageBean(int currentPage,int currentCount) throws SQLException;
    
    Page<Product> findProductListByCid(String cid,int currentPage ,int currentCount);
	Category selectCategoryByCid(String cid);
	List<Object> findProductByWord(String word) throws SQLException;
	List<Product> findProductListByName(String search) throws SQLException;
}
