package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.User;
import cn.service.IUserService;
import cn.service.imp.UserService;
import cn.util.MakeIdUtil;

/**
 * 用户交互
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		
		IUserService userService = new UserService();
		
		if("login".equals(method)) {
			//获得用户名和密码
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			User user = userService.login(name, password);
			
			if(user!=null) {
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("client/head.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "登录失败");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}
		}else if("register".equals(method)) {
			//封装用户注册信息
			//获得用户名和密码
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			User user = new User(MakeIdUtil.makeId(),name,password);
			
			boolean result = userService.register(user);
			if(result) {
				request.getRequestDispatcher("client/head.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "注册失败");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}
		}else if("Logout".equals(method)) {
			//退出登录
			//销毁Session
			request.getSession().invalidate();
			
			request.getRequestDispatcher("client/head.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
