package cn.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.dao.IOrderDao;
import cn.entity.Order;
import cn.entity.OrderItem;
import cn.util.DBUtil;

public class OrderDao implements IOrderDao {
	//订单处理 工具类
	/*
	 * 1.增加订单
	 * 2.根据id查找订单信息
	 * 3.获取所有订单（特定状态）
	 * 4.更新某用户的订单状态
	 * 5.获取某用户的所有订单
	 * 6.根据id取消订单
	 */

	//1.增加订单
	public boolean addOrder(Order order) {
		// TODO Auto-generated method stub
		//先插入订单（还需更新订单项）
		String sql1 = "insert into orders values(?,?,?,?,?)";
		//订单信息
		Object[] params1 = {order.getId(),order.getDate(),order.getUser_id(),order.getState(),order.getCost()};
		
		boolean flag1 = DBUtil.executeUpdate(sql1,params1);
		if(flag1) {
			//如果订单信息插入成功
			String sql2 = "insert into orderitem values(?,?,?,?,?)"; 
			
			//该订单包含的订单项
			Set<OrderItem> items = order.getItems();
			
			boolean flag2 = false;
			for(OrderItem item:items) {
				Object[] params2 = {item.getId(),item.getCost(),item.getQuantity(),order.getId(),item.getBook_id()};
				
				flag2 = DBUtil.executeUpdate(sql2,params2);
				
				//如果插入出错
				if(!flag2)  break;
			}
			return flag2;	
		}
		
		return false;
	}

	//2.根据id查找订单信息
	public Order getOrderById(String id) {
		// TODO Auto-generated method stub
		Order order = null;
		//先查询该订单信息
		try {
			String sql1 = "select * from orders where id = ?";
			Object[] params1 = {id};
			DBUtil.executeQuery(sql1, params1);
			if(DBUtil.rs.next()) {
				//如果存在该类别
				//获取除id外的其他信息
				Date date = DBUtil.rs.getDate("date");
				String user_id = DBUtil.rs.getString("user_id");
				boolean state = DBUtil.rs.getBoolean("state");
				Double cost = DBUtil.rs.getDouble("cost");
			
				order = new Order(id,date,state,user_id,cost);	
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		//查询订单项信息
		List<OrderItem> items = new ArrayList<OrderItem>();
		OrderItem item;
		try {
			//获得该订单包含的订单项
			String sql2 = "select * from orderitem where order_id = ?";
			Object[] params2 = {id};
			DBUtil.executeQuery(sql2, params2);
			while(DBUtil.rs.next()) {
				String idd =  DBUtil.rs.getString("id");
				Double costt =  DBUtil.rs.getDouble("cost");
				int quantity =  DBUtil.rs.getInt("quantity");
				String book_id =  DBUtil.rs.getString("book_id");
				
				item = new OrderItem(idd,costt,quantity,id,book_id);
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
//		System.out.println(items.size());
		//将订单表项加入订单对象
		order.getItems().addAll(items);
		return order;
	}

	//3.获取所有订单（特定状态）
	public List<Order> getAllOrders(boolean state) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		Order order = null;
		try {
			String sql = "select * from orders where state = ?";
			Object[] params = {state};
			DBUtil.executeQuery(sql, params);
			while(DBUtil.rs.next()) {
				//订单信息
				String id = DBUtil.rs.getString("id");
				Date date = DBUtil.rs.getDate("date");
				String user_id = DBUtil.rs.getString("user_id");
				Double cost = DBUtil.rs.getDouble("cost");
				
				order = new Order(id,date,state,user_id,cost);	
				orders.add(order);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return orders;	
	}

	//4.更新订单状态
	public boolean updateState(String id) {
		// TODO Auto-generated method stub
		String sql = "update orders set state = ? where id = ?";
		
		//原始都为false，支付后更改订单状态为true
		Object[] params = {true,id};
		return DBUtil.executeUpdate(sql,params);
	}

	//5.获取某用户的所有订单
	public List<Order> getUserOrderByid(String user_id) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		Order order = null;
		try {
			String sql = "select * from orders where user_id = ?";
			Object[] params = {user_id};
			DBUtil.executeQuery(sql, params);
			while(DBUtil.rs.next()) {
				//订单信息
				String id = DBUtil.rs.getString("id");
				Date date = DBUtil.rs.getDate("date");
				boolean state = DBUtil.rs.getBoolean("state");
				Double cost = DBUtil.rs.getDouble("cost");
				
				order = new Order(id,date,state,user_id,cost);	
				orders.add(order);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return orders;	
	}

	//6.根据id取消订单
	public boolean deleteOrder(String id) {
		// TODO Auto-generated method stub
		//只有未发货的订单可取消
		String sql = "delete from orders where id = ? and state = ?";
		Object[] params = {id,false};
		return DBUtil.executeUpdate(sql,params);
	}

}
