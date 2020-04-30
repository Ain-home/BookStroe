package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Cart;
import cn.service.IShopService;
import cn.service.imp.ShopService;

/**
 * ���ﳵ�ڷ���ɾ������գ���������
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		IShopService shopService = new ShopService();
		
		if("update".equals(method)) {
			//�޸ĸù�����
			String book_id = request.getParameter("book_id");
			String quantity = request.getParameter("quantity");
			
			shopService.updateQuantity(book_id, cart, quantity);
			
			request.getRequestDispatcher("client/showCart.jsp").forward(request, response);
		}else if("delete".equals(method)) {
			//ɾ���ù�����
			String book_id = request.getParameter("book_id");
			
			shopService.deleteBook(book_id, cart);
			
			request.getRequestDispatcher("client/showCart.jsp").forward(request, response);
		}else if("clear".equals(method)) {
			shopService.clearCart(cart);
			
			request.getRequestDispatcher("client/showCart.jsp").forward(request, response);
		}
		
//		request.setAttribute("message", "������æ");
//		request.getRequestDispatcher("client/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
