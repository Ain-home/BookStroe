package cn.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//购物车实体
	private Map<String,CartItem> bookMap = new LinkedHashMap<>();  //key为书的id，value为书
	
	//此购物车的总花费
	private double totalcost;
	
	public void addBook(Book book) {
		//添加购物项
		CartItem cartItem = bookMap.get(book.getId()); 
		
		if(cartItem == null) {
			//如果此购物项（书）不存在
			cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setQuantity(1);
			bookMap.put(book.getId(), cartItem);
		}else {
			//如果已经有了该购物项
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
			//将每个购物项的花费加起来，就是购物车的总花费 
			totalPrice += cartItem.getCost();
		}
		return totalPrice;
	}

	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}
}
