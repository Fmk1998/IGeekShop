package com.igeekshop.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.igeekshop.dao.IOrderDao;
import com.igeekshop.dao.impl.OrderDaoImpl;
import com.igeekshop.domain.Order;
import com.igeekshop.domain.OrderItem;
import com.igeekshop.service.IOrderService;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public class OrderServiceImpl implements IOrderService{
	private IOrderDao orderDaoImpl = new OrderDaoImpl();

	@Override
	public Order selectItemByOid(int oid) {
		return orderDaoImpl.selectItemByOid(oid);
	}
	@Override
	public List<Order> selectOrderByUid(int uid) {
		return orderDaoImpl.selectOrderByUid(uid);
	}
	@Override
	public List<Map<String, Object>> findAllItemsByOid(String oid) {
	    List<Map<String, Object>> mapList =null;
	    try {
	        mapList =  orderDaoImpl.findAllItemsByOid(oid);
	    } catch (SQLException e) {
	        
	        e.printStackTrace();
	    }
	    return mapList;
	}
	@Override
	public boolean insertOrder(Order order) {
		return orderDaoImpl.insertOrder(order)>0;
	}
	@Override
	public boolean updateOrderAdrr(Order order) {
		// TODO Auto-generated method stub
		return orderDaoImpl.updateOrderAdrr(order)>0;
	}
	@Override
	public boolean updateOrderState(String oid) {
		// TODO Auto-generated method stub
		return orderDaoImpl.updateOrderState(oid)>0;
	}
	@Override
	public boolean insertOrderItem(OrderItem orderitem) {
		// TODO Auto-generated method stub
		return orderDaoImpl.insertOrderItem(orderitem)>0;
	}

}
