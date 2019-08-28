package com.igeekshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.igeekshop.domain.Category;
import com.igeekshop.domain.Condition;
import com.igeekshop.domain.Page;
import com.igeekshop.domain.Product;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public interface IProductDao {
	Product selectProductByName(String pname);
	Product selectProductByPid(String pid);
	int insertProduct(Product product);
	int updateProduct(Product product);
	List<Product> selectAllProduct();
	List<Product> selectProductByCid(String cid);
	List<Product> findHotProductList() throws SQLException;
	List<Product> findNewProductList() throws SQLException;
	List<Category> findAllCategory() throws SQLException;
	//获得分页的商品数据
    List<Product> findProductListForPageBean(int index,int currentCount) throws SQLException;
	//获得全部的商品条数
    int getTotalCount() throws SQLException;
    int getTotalCount(String cid) throws SQLException;
    List<Product> findProductListByPage(String cid, int index, int currentCount) throws SQLException;
	Category selectCategoryByCid(String cid);
	List<Object> findProductByWord(String word) throws SQLException;
	List<Product> findProductListByName(String search) throws SQLException;
}
