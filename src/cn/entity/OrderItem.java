package cn.entity;

public class OrderItem {
	//������
	
	private String id;
	//�ö�����Ļ���
	private double cost;
	//�ö�������鱾����
	private int quantity;
	//�ö�������������
	private String book_id;
	//��������������
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
