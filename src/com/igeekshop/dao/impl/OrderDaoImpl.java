package com.igeekshop.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.igeekshop.common.utils.JDBCUtils;
import com.igeekshop.dao.IOrderDao;
import com.igeekshop.domain.Order;
import com.igeekshop.domain.OrderItem;
/**   
* @author:     Fukang
* @date    {date}{time}
*/
public class OrderDaoImpl implements IOrderDao{

	@Override
	public Order selectItemByOid(int oid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orderitem where oid = ?";
		Order order = null;
		try {
			order = queryRunner.query(sql, new BeanHandler<Order>(Order.class),oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> selectOrderByUid(int uid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where uid = ?";
		List<Order> orders = null;
		try {
			orders = queryRunner.query(sql,new BeanListHandler<Order>(Order.class),uid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return orders;
	}

	@Override
	public List<Map<String, Object>> findAllItemsByOid(String oid) throws SQLException {
		 QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		    String sql = "select i.count,i.subtotal,p.pname,p.pimage,p.shop_price"
		            + " from orderitem i,product p "
		            + "where i.pid=p.pid and oid=?";
		    List<Map<String, Object>> mapList = qr.query(sql,new MapListHandler(), oid);
		    return mapList;
	}

	@Override
	public int insertOrder(Order order) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into orders(oid,ordertime,total,state,address,name,telephone,uid) values(?,?,?,?,?,?,?,?)";
		int rs = 0;
		try {
			rs = queryRunner.update(sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public int updateOrderAdrr(Order order) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update orders set address=?,name=?,telephone=? where oid=?";
		int rs = 0;
		try {
			rs = queryRunner.update(sql, order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public int updateOrderState(String oid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update orders set state=? where oid=?";
		int rs = 0;
		try {
			rs = queryRunner.update(sql,1,oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}


	@Override
	public int insertOrderItem(OrderItem orderitem) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into orderitem(count,subtotal,pid,oid) values(?,?,?,?)";
		int rs = 0;
		try {
			rs = queryRunner.update(sql, orderitem.getCount(),orderitem.getSubtotal(),orderitem.getProduct().getPid(),orderitem.getOrder().getOid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
