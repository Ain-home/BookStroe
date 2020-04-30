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
 * ��ת�����ﳵ
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
		//���û�Ϊ��¼�����޷��鿴���ﳵ��
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message", "����û��¼�أ�");
			request.getRequestDispatcher("client/message.jsp").forward(request, response);
			return;
		}
		
		//������¼�ˣ��Ϳ�����ʾ���û��Ĺ��ﳵ
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		request.setAttribute("cart", cart);
		request.getRequestDispatcher("client/showCart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
