package cn.dao;

import java.util.List;

import cn.entity.Order;

public interface IOrderDao {
	//订单  工具接口
	
	public boolean addOrder(Order order);
	
	public Order getOrderById(String id);
	
	public List<Order> getAllOrders(boolean state);
	
	public boolean updateState(String user_id);
	
	public List<Order> getUserOrderByid(String user_id);
	
	public boolean deleteOrder(String id);

}
