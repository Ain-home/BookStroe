package cn.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//���ﳵʵ��
	private Map<String,CartItem> bookMap = new LinkedHashMap<>();  //keyΪ���id��valueΪ��
	
	//�˹��ﳵ���ܻ���
	private double totalcost;
	
	public void addBook(Book book) {
		//��ӹ�����
		CartItem cartItem = bookMap.get(book.getId()); 
		
		if(cartItem == null) {
			//����˹�����飩������
			cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setQuantity(1);
			bookMap.put(book.getId(), cartItem);
		}else {
			//����Ѿ����˸ù�����
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}
	}

	public Map<String, CartItem> getBookMap() {
		return bookMap;
	}

	public void setBookMap(Map<String, CartItem> bookMap) {
		this.bookMap = bookMap;
	}

	public double getTotalcost() {
		double totalPrice = 0;
		for(Map.Entry<String, CartItem> one:bookMap.entrySet()) {
			CartItem cartItem = one.getValue();
			//��ÿ��������Ļ��Ѽ����������ǹ��ﳵ���ܻ��� 
			totalPrice += cartItem.getCost();
		}
		return totalPrice;
	}

	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}
}
