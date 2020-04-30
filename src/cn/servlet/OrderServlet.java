package cn.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Book;
import cn.entity.Cart;
import cn.entity.CartItem;
import cn.entity.Order;
import cn.entity.OrderItem;
import cn.entity.User;
import cn.service.IBookService;
import cn.service.IOrderService;
import cn.service.imp.BookService;
import cn.service.imp.OrderService;

/**
 * ǰ̨��������
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		
		IOrderService orderService = new OrderService();
		
		if("create".equals(method)) {
			//��ȡ���ﳵ��ѡ�鼮
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			User user = (User) request.getSession().getAttribute("user");
			
			boolean result = orderService.createOrder(cart, user);
			if(result) {
				request.setAttribute("message", "���������ɣ���ǰ������ҳ���ٴβ鿴�����Ǯ�ɣ�");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "ϵͳ��æ���Ժ������԰ɣ�");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}
		}else if("find".equals(method)){
			User user = (User) request.getSession().getAttribute("user");
			//�ж��û��Ƿ��¼��ֻ�е�¼����ܲ鿴������Ϣ
			if(user==null) {
				request.setAttribute("message", "���ȵ�¼���������ɣ�");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
				return;
			}
			//����û���¼��
			List<Order> orders = orderService.getUserOrderByid(user.getId());
			
			request.setAttribute("user", user);
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("client/showOrder.jsp").forward(request, response);
		}else if("delete".equals(method)) {
			String id = request.getParameter("order_id");
			
			boolean result = orderService.deleteOrder(id);
			if(result) {
				//�ɹ�ɾ��һ�����
				User user = (User) request.getSession().getAttribute("user");
				List<Order> orders = orderService.getUserOrderByid(user.getId());
				
				request.setAttribute("user", user);
				request.setAttribute("orders", orders);
				request.getRequestDispatcher("client/showOrder.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "ϵͳ��æ���Ժ������԰ɣ�");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}
		}else if("detail".equals(method)) {
			String id = request.getParameter("order_id");
			
			IBookService bookService = new BookService();
			
			//�洢�ö������鼮����
			List<CartItem> cartItems = new ArrayList<CartItem>();
			Book book = null;
			CartItem cartItem = null;
			
			//��ȡ��id��������ϸ��Ϣ
			Order order = orderService.getOrderById(id);
			Set<OrderItem> items = order.getItems();
			for(OrderItem item:items) {
				String book_id = item.getBook_id();
				book = bookService.getBookById(book_id);
				
				//�Թ���������ʽ�洢
				cartItem = new CartItem(book,item.getQuantity(),item.getCost());
				cartItems.add(cartItem);
			}
			
			request.setAttribute("cartItems", cartItems);
			request.getRequestDispatcher("client/orderInfo.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
