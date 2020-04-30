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
	//�������� ������
	/*
	 * 1.���Ӷ���
	 * 2.����id���Ҷ�����Ϣ
	 * 3.��ȡ���ж������ض�״̬��
	 * 4.����ĳ�û��Ķ���״̬
	 * 5.��ȡĳ�û������ж���
	 * 6.����idȡ������
	 */

	//1.���Ӷ���
	public boolean addOrder(Order order) {
		// TODO Auto-generated method stub
		//�Ȳ��붩����������¶����
		String sql1 = "insert into orders values(?,?,?,?,?)";
		//������Ϣ
		Object[] params1 = {order.getId(),order.getDate(),order.getUser_id(),order.getState(),order.getCost()};
		
		boolean flag1 = DBUtil.executeUpdate(sql1,params1);
		if(flag1) {
			//���������Ϣ����ɹ�
			String sql2 = "insert into orderitem values(?,?,?,?,?)"; 
			
			//�ö��������Ķ�����
			Set<OrderItem> items = order.getItems();
			
			boolean flag2 = false;
			for(OrderItem item:items) {
				Object[] params2 = {item.getId(),item.getCost(),item.getQuantity(),order.getId(),item.getBook_id()};
				
				flag2 = DBUtil.executeUpdate(sql2,params2);
				
				//����������
				if(!flag2)  break;
			}
			return flag2;	
		}
		
		return false;
	}

	//2.����id���Ҷ�����Ϣ
	public Order getOrderById(String id) {
		// TODO Auto-generated method stub
		Order order = null;
		//�Ȳ�ѯ�ö�����Ϣ
		try {
			String sql1 = "select * from orders where id = ?";
			Object[] params1 = {id};
			DBUtil.executeQuery(sql1, params1);
			if(DBUtil.rs.next()) {
				//������ڸ����
				//��ȡ��id���������Ϣ
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
		//��ѯ��������Ϣ
		List<OrderItem> items = new ArrayList<OrderItem>();
		OrderItem item;
		try {
			//��øö��������Ķ�����
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
		//������������붩������
		order.getItems().addAll(items);
		return order;
	}

	//3.��ȡ���ж������ض�״̬��
	public List<Order> getAllOrders(boolean state) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		Order order = null;
		try {
			String sql = "select * from orders where state = ?";
			Object[] params = {state};
			DBUtil.executeQuery(sql, params);
			while(DBUtil.rs.next()) {
				//������Ϣ
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

	//4.���¶���״̬
	public boolean updateState(String id) {
		// TODO Auto-generated method stub
		String sql = "update orders set state = ? where id = ?";
		
		//ԭʼ��Ϊfalse��֧������Ķ���״̬Ϊtrue
		Object[] params = {true,id};
		return DBUtil.executeUpdate(sql,params);
	}

	//5.��ȡĳ�û������ж���
	public List<Order> getUserOrderByid(String user_id) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		Order order = null;
		try {
			String sql = "select * from orders where user_id = ?";
			Object[] params = {user_id};
			DBUtil.executeQuery(sql, params);
			while(DBUtil.rs.next()) {
				//������Ϣ
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

	//6.����idȡ������
	public boolean deleteOrder(String id) {
		// TODO Auto-generated method stub
		//ֻ��δ�����Ķ�����ȡ��
		String sql = "delete from orders where id = ? and state = ?";
		Object[] params = {id,false};
		return DBUtil.executeUpdate(sql,params);
	}

}
