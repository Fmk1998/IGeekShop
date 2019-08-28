package com.igeekshop.service;

import java.util.List;
import java.util.Map;

import com.igeekshop.domain.Order;
import com.igeekshop.domain.OrderItem;

/**   
* @author:     Fukang
* @date    {date}{time}
*/
public interface IOrderService {
	Order selectItemByOid(int oid);
	List<Order> selectOrderByUid(int uid);
	List<Map<String, Object>> findAllItemsByOid(String oid);
	boolean insertOrder(Order order);
	boolean updateOrderAdrr(Order order);
	boolean updateOrderState(String oid);
	boolean insertOrderItem(OrderItem orderitem);
}
