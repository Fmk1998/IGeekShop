package com.igeekshop.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.igeekshop.domain.Category;
import com.igeekshop.domain.Condition;
import com.igeekshop.domain.Order;
import com.igeekshop.domain.Product;
import com.igeekshop.domain.User;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public interface IAdminProductService {
	public User AdminLogin(String username) throws SQLException;
	public List<Product> findAllProduct() throws SQLException;
	public List<Category> findAllCategory() throws SQLException;
	public boolean addProduct(Product product) throws SQLException;
	public boolean addCategory(String cname) throws SQLException;
	public boolean delProduct(String pid) throws SQLException;
	public Product editProduct(String pid) throws SQLException;
	public boolean editProductSave(Product product) throws SQLException;
	public boolean editCategorySave(Category category) throws SQLException;
	public boolean delCategory(String cid) throws SQLException;
	public List<Product> findProductListByCondition(Condition condition) throws SQLException;
	public List<Order> findAllOrder() throws SQLException;
	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException;
}
