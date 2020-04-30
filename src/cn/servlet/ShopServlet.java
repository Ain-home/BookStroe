package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Book;
import cn.entity.Cart;
import cn.entity.User;
import cn.service.IBookService;
import cn.service.IShopService;
import cn.service.imp.BookService;
import cn.service.imp.ShopService;

/**
 * ���������
 * Servlet implementation class ShopServlet
 */
@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		//����֮ǰ���ȵ�¼���ж��û��Ƿ��¼
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message", "���ȵ�¼������shopping�ɣ�");
			request.getRequestDispatcher("client/message.jsp").forward(request, response);
			return;
		}
		
		//�����¼��
		//��ȡѡ�е��鼮id
		String book_id = request.getParameter("book_id");
		
		IBookService bookService = new BookService();
		Book book = bookService.getBookById(book_id);
		
		//�жϸ��û��Ƿ��Ѵ������ﳵ(Cart
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart==null) {
			//������û���һ�ι������ǰ�޹��ﳵ
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		IShopService shopService = new ShopService();
		//�����������뵽���ﳵ
		shopService.buyBook(book, cart);
		
		request.setAttribute("message", "����Ʒ���ڹ��ﳵ��������");
		request.getRequestDispatcher("client/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
