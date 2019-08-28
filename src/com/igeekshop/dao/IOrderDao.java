package com.igeekshop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.igeekshop.domain.Order;
import com.igeekshop.domain.OrderItem;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public interface IOrderDao {
	Order selectItemByOid(int oid);
	int insertOrder(Order order);
	int insertOrderItem(OrderItem orderitem);
	int updateOrderAdrr(Order order);
	int updateOrderState(String oid);
	List<Order> selectOrderByUid(int uid);
	List<Map<String, Object>> findAllItemsByOid(String oid) throws SQLException;
}
