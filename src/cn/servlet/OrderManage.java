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
import cn.entity.CartItem;
import cn.entity.Order;
import cn.entity.OrderItem;
import cn.service.IBookService;
import cn.service.IOrderService;
import cn.service.imp.BookService;
import cn.service.imp.OrderService;

/**
 * 后台管理人员处理订单
 * Servlet implementation class OrderManage
 */
@WebServlet("/OrderManage")
public class OrderManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		
		IOrderService orderService = new OrderService();
		
		if("detail".equals(method)) {
			String id = request.getParameter("order_id");
			String state = request.getParameter("state");
			
			//获取对应订单
			Order order = orderService.getOrderById(id);
			
			IBookService bookService = new BookService();
			
			//存储该订单的书籍详情
			List<CartItem> cartItems = new ArrayList<CartItem>();
			Book book = null;
			CartItem cartItem = null;
			
			Set<OrderItem> items = order.getItems();
			for(OrderItem item:items) {
				String book_id = item.getBook_id();
				book = bookService.getBookById(book_id);
				
				//以购物表项的形式存储
				cartItem = new CartItem(book,item.getQuantity(),item.getCost());
				cartItems.add(cartItem);
			}
			
			request.setAttribute("state", state);
			request.setAttribute("order_id", id);
			request.setAttribute("cartItems", cartItems);
			request.getRequestDispatcher("server/orderDetail.jsp").forward(request, response);
		}else if("delete".equals(method)) {
			String id = request.getParameter("order_id");
			String state = request.getParameter("state");
			
			boolean result = orderService.deleteOrder(id);
			if(result) {
				//成功删除一项订单后
				List<Order> list = null;
				Boolean s = false;
				if("true".equals(state)) {
					s = true;
					list = orderService.getAllOrders(s);
				}else if("false".equals(state)) {
					s = false;
					list = orderService.getAllOrders(s);
				}
						
				request.setAttribute("list", list);
				request.getRequestDispatcher("server/showOrders.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "系统出错！");
				request.getRequestDispatcher("server/message.jsp").forward(request, response);
			}
		}else if("send".equals(method)) {
			String order_id = request.getParameter("order_id");
			
			boolean result = orderService.updateState(order_id);
			if(result) {
				request.setAttribute("message", "该订单已成功发货！");
			}else {
				request.setAttribute("message", "系统出错！");
			}
			request.getRequestDispatcher("server/message.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
