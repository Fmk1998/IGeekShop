package com.igeekshop.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.igeekshop.common.utils.DataSourceUtils;
import com.igeekshop.common.utils.JDBCUtils;
import com.igeekshop.dao.IProductDao;
import com.igeekshop.domain.Category;
import com.igeekshop.domain.Page;
import com.igeekshop.domain.Product;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public class ProductDaoImpl implements IProductDao{

	@Override
	public Product selectProductByName(String pname) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pname = ?";
		Product product = null;
		try {
			product = queryRunner.query(sql, new BeanHandler<Product>(Product.class),pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public int insertProduct(Product product) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into product(pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,pflag) values(?,?,?,?,?,?,?,?)";
		int rs = 0;
		try {
			rs = queryRunner.update(sql, product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public int updateProduct(Product product) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=? where code=?";
		int rs = 0;
		try {
			rs = queryRunner.update(sql, product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List<Product> selectAllProduct() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product";
		List<Product> products = null;
		try {
			products = queryRunner.query(sql,new BeanListHandler<Product>(Product.class));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return products;
	}

	@Override
	public List<Product> selectProductByCid(String cid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where cid=?";
		List<Product> products = null;
		try {
			products = queryRunner.query(sql,new BeanListHandler<Product>(Product.class),cid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return products;
	}

	@Override
	public Product selectProductByPid(String pid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product product = null;
		try {
			product = queryRunner.query(sql, new BeanHandler<Product>(Product.class),pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> findHotProductList() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where is_hot=? limit ?,?";
        List<Product> hotProductList = qr.query(sql, new BeanListHandler<Product>(Product.class), 1,0,9);
        return hotProductList;
	}

	@Override
	public List<Product> findNewProductList() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product order by pdate desc limit ?,?";
        List<Product> newProductList = qr.query(sql, new BeanListHandler<Product>(Product.class), 0,9);
        return newProductList;
	}

	@Override
	public List<Category> findAllCategory() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	    String sql = "select * from category";
	    List<Category> categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
	    return categoryList;
	}

	@Override
	public Category selectCategoryByCid(String cid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from category where cid = ?";
		Category category = null;
		try {
			category = queryRunner.query(sql, new BeanHandler<Category>(Category.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
    //获得全部的商品条数
	public List<Product> findProductListForPageBean(int index, int currentCount) throws SQLException {
		 QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
	     String sql = "select * from product limit ?,?";
	     return runner.query(sql, new BeanListHandler<Product>(Product.class), index,currentCount);
	}

	@Override
	//获得分页的商品数据
	public int getTotalCount() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product";
        Long query = (Long) runner.query(sql, new ScalarHandler());
        return query.intValue();
	}

	@Override
	public int getTotalCount(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from product where cid =?";
        Long totalCount = (Long) qr.query(sql, new ScalarHandler(),cid);
        return totalCount.intValue();
	}

	@Override
	public List<Product> findProductListByPage(String cid, int index, int currentCount) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where cid=? limit ?,?";
        List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), cid,index,currentCount);
        return list;
	}

	@Override
	public List<Object> findProductByWord(String word) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pname like ? limit 0,8";
		List<Object> query = (List<Object>) runner.query(sql, new ColumnListHandler("pname"), "%"+word+"%");//模糊查询
		return query; 
	}

	@Override
	public List<Product> findProductListByName(String search) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        //定义一个存储实际参数的容器
        List<String> list = new ArrayList<String>();
        String sql = "select * from product where 1=1 ";
        // 判断用户是否输入了商品名称，并且要去除两端的空格
        if(search!=null&&!search.equals("")){
            //防止连接到了一起，最好加上问号。
            sql+=" and pname like ?";
            //将传递的参数存储到list集合中
            list.add("%"+search.trim()+"%");
        }
        List<Product> productList =  qr.query(sql, new BeanListHandler<Product>(Product.class) ,list.toArray());
        return productList;
	}


}
