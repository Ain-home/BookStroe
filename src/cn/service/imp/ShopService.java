package cn.service.imp;

import cn.entity.Book;
import cn.entity.Cart;
import cn.service.IShopService;

public class ShopService implements IShopService {

	@Override
	public void buyBook(Book book, Cart cart) {
		// TODO Auto-generated method stub
		cart.addBook(book);
	}

	@Override
	public void deleteBook(String id, Cart cart) {
		// TODO Auto-generated method stub
		cart.getBookMap().remove(id);
	}

	@Override
	public void updateQuantity(String id, Cart cart, String quantity) {
		// TODO Auto-generated method stub
		cart.getBookMap().get(id).setQuantity(Integer.parseInt(quantity));
	}

	@Override
	public void clearCart(Cart cart) {
		// TODO Auto-generated method stub
		cart.getBookMap().clear();
	}
	

}
