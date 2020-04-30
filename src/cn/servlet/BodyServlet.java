package cn.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Category;
import cn.entity.Page;
import cn.service.IBookService;
import cn.service.ICategoryService;
import cn.service.imp.BookService;
import cn.service.imp.CategoryService;

/**
 * 前端显示
 * Servlet implementation class BodyServlet
 */
@WebServlet("/BodyServlet")
public class BodyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BodyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		//获得所有类别信息
		ICategoryService categoryService = new CategoryService();
		List<Category> categories = categoryService.getAllCategories();
		request.setAttribute("categories", categories);
		
		//获得所有图书信息
		String currentPageCount = request.getParameter("currentPageCount");
		IBookService bookService = new BookService();
		Page page = bookService.getPageBooks(currentPageCount);
		page.setUrl(request.getRequestURI());
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("client/body.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
