package cn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Category;
import cn.service.ICategoryService;
import cn.service.imp.CategoryService;

/**
 * 1.添加书籍时转交类别列表给页面
 * Servlet implementation class BookTransfer
 */
@WebServlet("/BookTransfer")
public class BookTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		
		if("addUI".equals(method)) {
			ICategoryService categoryService = new CategoryService();
			List<Category> list = categoryService.getAllCategories();
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("server/addBook.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
