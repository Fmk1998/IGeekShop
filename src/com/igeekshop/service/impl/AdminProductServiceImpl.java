package com.igeekshop.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.igeekshop.dao.IAdminProduct;
import com.igeekshop.dao.impl.AdminProductDaoImpl;
import com.igeekshop.domain.Category;
import com.igeekshop.domain.Condition;
import com.igeekshop.domain.Order;
import com.igeekshop.domain.Product;
import com.igeekshop.domain.User;
import com.igeekshop.service.IAdminProductService;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public class AdminProductServiceImpl implements IAdminProductService{
	private IAdminProduct AdminProductDao = new AdminProductDaoImpl();
	@Override
	public List<Product> findAllProduct() throws SQLException {
		return  AdminProductDao.findAllProduct();
	}
	@Override
	public List<Category> findAllCategory() throws SQLException {
		return  AdminProductDao.findAllCategory();
	}
	@Override
	public boolean addProduct(Product product) throws SQLException {
		return AdminProductDao.addProduct(product);
	}
	@Override
	public boolean delProduct(String pid) throws SQLException {
		return AdminProductDao.delProduct(pid);
	}
	@Override
	public Product editProduct(String pid) throws SQLException {
        return AdminProductDao.editProduct(pid);
	}
	@Override
	public boolean editProductSave(Product product) throws SQLException {
		return AdminProductDao.editProductSave(product);
	}
	@Override
	public List<Product> findProductListByCondition(Condition condition) throws SQLException {
		return AdminProductDao.findProductListByCondition(condition);
	}
	@Override
	public boolean delCategory(String cid) throws SQLException {
		return AdminProductDao.delCategory(cid);
	}
	@Override
	public boolean addCategory(String cname) throws SQLException {
		// TODO Auto-generated method stub
		return AdminProductDao.addCategory(cname);
	}
	@Override
	public boolean editCategorySave(Category category) throws SQLException {
		// TODO Auto-generated method stub
		return AdminProductDao.editCategorySave(category);
	}
	@Override
	public List<Order> findAllOrder() throws SQLException {
		// TODO Auto-generated method stub
		return AdminProductDao.findAllOrder();
	}
	@Override
	public User AdminLogin(String username) throws SQLException {
		// TODO Auto-generated method stub
		return AdminProductDao. AdminLogin(username);
	}
	@Override
	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException {
		return AdminProductDao.findOrderInfoByOid(oid);
	}
	
}
