package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Cart;
import cn.entity.User;

/**
 * 跳转至购物车
 * Servlet implementation class CartTransfer
 */
@WebServlet("/CartTransfer")
public class CartTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CartTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//若用户为登录，则无法查看购物车等
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message", "您还没登录呢！");
			request.getRequestDispatcher("client/message.jsp").forward(request, response);
			return;
		}
		
		//若果登录了，就可以显示该用户的购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("client/showCart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
