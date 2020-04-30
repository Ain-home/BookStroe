package cn.service;

import java.util.List;

import cn.entity.Cart;
import cn.entity.Order;
import cn.entity.User;

public interface IOrderService {
	//��������
	 
	//��������
	public boolean createOrder(Cart cart,User user);
	
	public Order getOrderById(String id);
	
	public List<Order> getAllOrders(boolean state);
	
	public boolean updateState(String id);
	
	public List<Order> getUserOrderByid(String user_id);

	public boolean deleteOrder(String id);
}
