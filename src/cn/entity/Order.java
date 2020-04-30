package cn.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
	//����������������������
	
	private String id;
	//�µ������ڣ�״̬��֧����
	private Date date;
	private boolean state;
	//���������û�
	private String user_id;
	//�ö����ܷ���
	private double cost;
	
	//�ö����ɶ�����������
	private Set<OrderItem> items = new HashSet<>();
	
	public Order() {
	
	}

	public Order(String id, Date date, boolean state, String user_id, double cost) {
		this.id = id;
		this.date = date;
		this.state = state;
		this.user_id = user_id;
		this.cost = cost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

}
