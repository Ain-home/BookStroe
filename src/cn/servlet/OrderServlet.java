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
 * 前台订单服务
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
			//获取购物车已选书籍
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			User user = (User) request.getSession().getAttribute("user");
			
			boolean result = orderService.createOrder(cart, user);
			if(result) {
				request.setAttribute("message", "订单已生成，请前往订单页面再次查看无误后付钱吧！");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "系统繁忙，稍后再试试吧！");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}
		}else if("find".equals(method)){
			User user = (User) request.getSession().getAttribute("user");
			//判断用户是否登录，只有登录后才能查看订单信息
			if(user==null) {
				request.setAttribute("message", "请先登录再来看看吧！");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
				return;
			}
			//如果用户登录了
			List<Order> orders = orderService.getUserOrderByid(user.getId());
			
			request.setAttribute("user", user);
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("client/showOrder.jsp").forward(request, response);
		}else if("delete".equals(method)) {
			String id = request.getParameter("order_id");
			
			boolean result = orderService.deleteOrder(id);
			if(result) {
				//成功删除一项订单后
				User user = (User) request.getSession().getAttribute("user");
				List<Order> orders = orderService.getUserOrderByid(user.getId());
				
				request.setAttribute("user", user);
				request.setAttribute("orders", orders);
				request.getRequestDispatcher("client/showOrder.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "系统繁忙，稍后再试试吧！");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}
		}else if("detail".equals(method)) {
			String id = request.getParameter("order_id");
			
			IBookService bookService = new BookService();
			
			//存储该订单的书籍详情
			List<CartItem> cartItems = new ArrayList<CartItem>();
			Book book = null;
			CartItem cartItem = null;
			
			//获取该id订单的详细信息
			Order order = orderService.getOrderById(id);
			Set<OrderItem> items = order.getItems();
			for(OrderItem item:items) {
				String book_id = item.getBook_id();
				book = bookService.getBookById(book_id);
				
				//以购物表项的形式存储
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
