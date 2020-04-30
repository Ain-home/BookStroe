package cn.service;

import cn.entity.Book;
import cn.entity.Cart;

public interface IShopService {
	//购物服务
	
	//1.根据id，购买书
	public void buyBook(Book book, Cart cart);		
	
	//2.根据id删除购物车中的书
	public void deleteBook(String id, Cart cart);
	
	//3.根据用户在购物车修改的该购物项更新购物车
	public void updateQuantity(String id, Cart cart, String quantity);

	//4.清空购物车
	public void clearCart(Cart cart);
}
