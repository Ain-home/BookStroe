package cn.service;

import cn.entity.Book;
import cn.entity.Cart;

public interface IShopService {
	//�������
	
	//1.����id��������
	public void buyBook(Book book, Cart cart);		
	
	//2.����idɾ�����ﳵ�е���
	public void deleteBook(String id, Cart cart);
	
	//3.�����û��ڹ��ﳵ�޸ĵĸù�������¹��ﳵ
	public void updateQuantity(String id, Cart cart, String quantity);

	//4.��չ��ﳵ
	public void clearCart(Cart cart);
}
