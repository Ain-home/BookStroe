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
 * 处理购物服务
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
		
		//购买之前得先登录，判断用户是否登录
		User user = (User) request.getSession().getAttribute("user");
		if(user==null) {
			request.setAttribute("message", "请先登录了再来shopping吧！");
			request.getRequestDispatcher("client/message.jsp").forward(request, response);
			return;
		}
		
		//如果登录了
		//获取选中得书籍id
		String book_id = request.getParameter("book_id");
		
		IBookService bookService = new BookService();
		Book book = bookService.getBookById(book_id);
		
		//判断该用户是否已创建购物车(Cart
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart==null) {
			//如果该用户第一次购物，即此前无购物车
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		IShopService shopService = new ShopService();
		//将购买的书加入到购物车
		shopService.buyBook(book, cart);
		
		request.setAttribute("message", "该商品已在购物车等亲啦！");
		request.getRequestDispatcher("client/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
