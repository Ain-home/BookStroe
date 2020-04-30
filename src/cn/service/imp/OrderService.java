package cn.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.dao.IOrderDao;
import cn.dao.imp.OrderDao;
import cn.entity.Cart;
import cn.entity.CartItem;
import cn.entity.Order;
import cn.entity.OrderItem;
import cn.entity.User;
import cn.service.IOrderService;
import cn.util.MakeIdUtil;

public class OrderService implements IOrderService {
	
	IOrderDao orderDao = new OrderDao();

	@Override
	public boolean createOrder(Cart cart, User user) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setId(MakeIdUtil.makeId());
		order.setDate(new Date());
		order.setCost(cart.getTotalcost());
		order.setState(false);
		order.setUser_id(user.getId());
		
		//该购物单的所有表单项
		for(Map.Entry<String, CartItem> m:cart.getBookMap().entrySet()) {
			OrderItem item = new OrderItem();
			CartItem citem = m.getValue();
			
			item.setId(MakeIdUtil.makeId());
			item.setCost(citem.getCost());
			item.setQuantity(citem.getQuantity());
			item.setBook_id(citem.getBook().getId());
			item.setOrder_id(order.getId());
			
			order.getItems().add(item);
		}
		
		return orderDao.addOrder(order);
	}

	@Override
	public Order getOrderById(String id) {
		// TODO Auto-generated method stub
		return orderDao.getOrderById(id);
	}

	@Override
	public List<Order> getAllOrders(boolean state) {
		// TODO Auto-generated method stub
		return orderDao.getAllOrders(state);
	}

	@Override
	public boolean updateState(String id) {
		// TODO Auto-generated method stub
		return orderDao.updateState(id);
	}

	@Override
	public List<Order> getUserOrderByid(String user_id) {
		// TODO Auto-generated method stub
		return orderDao.getUserOrderByid(user_id);
	}

	@Override
	public boolean deleteOrder(String id) {
		// TODO Auto-generated method stub
		return orderDao.deleteOrder(id);
	}

}
