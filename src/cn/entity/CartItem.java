package cn.entity;

public class CartItem {
	//�������
	private Book book;
	private int quantity;
	
	//�ù�������ܼ۸���ڸ���Ʒ����*����
	private double cost;

	public CartItem() {
	
	}

	public CartItem(Book book, int quantity, double cost) {
		this.book = book;
		this.quantity = quantity;
		this.cost = cost;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return this.quantity*book.getPrice();
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
