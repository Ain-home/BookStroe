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
 * �û�����
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
			//����û���������
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			
			User user = userService.login(name, password);
			
			if(user!=null) {
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("client/head.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "��¼ʧ��");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}
		}else if("register".equals(method)) {
			//��װ�û�ע����Ϣ
			//����û���������
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			User user = new User(MakeIdUtil.makeId(),name,password);
			
			boolean result = userService.register(user);
			if(result) {
				request.getRequestDispatcher("client/head.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "ע��ʧ��");
				request.getRequestDispatcher("client/message.jsp").forward(request, response);
			}
		}else if("Logout".equals(method)) {
			//�˳���¼
			//����Session
			request.getSession().invalidate();
			
			request.getRequestDispatcher("client/head.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
