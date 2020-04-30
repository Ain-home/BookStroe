package cn.entity;

public class OrderItem {
	//订单项
	
	private String id;
	//该订单项的花费
	private double cost;
	//该订单项的书本数量
	private int quantity;
	//该订单项所属的书
	private String book_id;
	//订单项所属订单
	private String order_id;
	
	public OrderItem() {

	}
	public OrderItem(String id, double cost, int quantity,String order_id, String book_id) {
		this.id = id;
		this.cost = cost;
		this.quantity = quantity;
		this.book_id = book_id;
		this.order_id = order_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	

}
