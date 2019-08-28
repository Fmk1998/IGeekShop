package com.igeekshop.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.igeekshop.common.utils.JDBCUtils;
import com.igeekshop.dao.IAdminProduct;
import com.igeekshop.domain.Category;
import com.igeekshop.domain.Condition;
import com.igeekshop.domain.Order;
import com.igeekshop.domain.Product;
import com.igeekshop.domain.User;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public class AdminProductDaoImpl implements IAdminProduct{

	@Override
	public List<Product> findAllProduct() throws SQLException {
		 QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	     String sql = "select * from product";
	     List<Product> productList = qr.query(sql, new BeanListHandler<Product>(Product.class));
	     return productList;
	}

	@Override
	public List<Category> findAllCategory() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from category";
        List<Category> categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
        return categoryList;
	}

	@Override
	public boolean addProduct(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into product(pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,pflag,cid) values(?,?,?,?,?,?,?,?,?)";
        Object[] params = {product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
        int num = qr.update(sql, params);
        if(num>0){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public boolean delProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from product where pid=?";
        int num = qr.update(sql, pid);
        if(num>0){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public Product editProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pid=?";
        Product product = qr.query(sql, new BeanHandler<Product>(Product.class),pid);
        return product;
	}

	@Override
	public boolean editProductSave(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
        int num = qr.update(sql, product.getPname(),product.getMarket_price(),
                product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),
                product.getPdesc(),product.getPflag(),product.getCid(),product.getPid());
        System.out.println(num);
        if(num>0){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public List<Product> findProductListByCondition(Condition condition) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        //定义一个存储实际参数的容器
        List<String> list = new ArrayList<String>();
        String sql = "select * from product where 1=1 ";
        // 判断用户是否输入了商品名称，并且要去除两端的空格
        if(condition.getPname()!=null&&!condition.getPname().trim().equals("")){
            //防止连接到了一起，最好加上问号。
            sql+=" and pname like ?";
            //将传递的参数存储到list集合中
            list.add("%"+condition.getPname().trim()+"%");
        }
        if(condition.getIsHot()!=null&&!condition.getIsHot().trim().equals("")){
            sql+=" and is_hot=? ";
            list.add(condition.getIsHot().trim());
        }
        if(condition.getCid()!=null&&!condition.getCid().trim().equals("")){
            sql+=" and cid=? ";
            list.add(condition.getCid().trim());
        }
        List<Product> productList =  qr.query(sql, new BeanListHandler<Product>(Product.class) ,list.toArray());
        return productList;
	}

	@Override
	public boolean delCategory(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "delete from category where cid=?";
        int num = qr.update(sql, cid);
        if(num>0){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public boolean addCategory(String cname) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into category(cname) values(?)";
        int num = qr.update(sql, cname);
        if(num>0){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public boolean editCategorySave(Category category) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "update category set cname=? where cid=?";
        int num = qr.update(sql,category.getCname(),category.getCid());
        System.out.println(num);
        if(num>0){
            return true;
        }else{
            return false;
        }
	}

	@Override
	public List<Order> findAllOrder() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from orders";
        List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
        return orderList;
	}

	@Override
	public User AdminLogin(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from admin where username=?";
        User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException {
		QueryRunner  qr = new QueryRunner(JDBCUtils.getDataSource());
	    String sql ="select p.pimage,p.pname,p.shop_price,i.count,i.subtotal "
	            + " from orderitem i,product p "
	            + " where i.pid=p.pid and i.oid=? ";
	    return qr.query(sql, new MapListHandler(),oid);
	}

}
