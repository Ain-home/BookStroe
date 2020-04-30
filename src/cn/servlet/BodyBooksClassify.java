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
 * 前端书籍分类查询
 * Servlet implementation class BodyBooksClassify
 */
@WebServlet("/BodyBooksClassify")
public class BodyBooksClassify extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BodyBooksClassify() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String currentPageCount = request.getParameter("currentPageCount");
        String category_id = request.getParameter("category_id");
       
        ICategoryService categoryService = new CategoryService();
		List<Category> categories = categoryService.getAllCategories();
		
        IBookService bookService = new BookService();
        Page page = bookService.getPageBooks(currentPageCount, category_id);
        page.setUrl(request.getRequestURI());
        
        request.setAttribute("category_id", category_id);
        request.setAttribute("categories", categories);
        request.setAttribute("page", page);
        request.getRequestDispatcher("client/body.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
