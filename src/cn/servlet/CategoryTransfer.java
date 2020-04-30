package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Category;
import cn.service.ICategoryService;
import cn.service.imp.CategoryService;

/**
 * CategoryTransfer：修改类别信息的中转站
 * 获取要修改的Category类原本的数据
 * Servlet implementation class CategoryTransfer
 */
@WebServlet("/CategoryTransfer")
public class CategoryTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CategoryTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		ICategoryService categoryService = new CategoryService();
		Category category = categoryService.getCategoryById(id);
		
		request.setAttribute("category", category);
		request.getRequestDispatcher("server/CategoryInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
