package cn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Order;
import cn.service.IOrderService;
import cn.service.imp.OrderService;

/**
 * ��̨������ʾ��������δ����
 * Servlet implementation class OrderTransfer
 */
@WebServlet("/OrderTransfer")
public class OrderTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String state = request.getParameter("state");
		
		IOrderService orderService = new OrderService();
		//��Ÿö�״̬����
		List<Order> list;
		if("true".equals(state)) {
			list = orderService.getAllOrders(true);
			request.setAttribute("list", list);
		}else if("false".equals(state)) {
			list = orderService.getAllOrders(false);
			request.setAttribute("list", list);
		}
		request.getRequestDispatcher("server/showOrders.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
