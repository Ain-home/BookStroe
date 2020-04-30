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
import cn.util.MakeIdUtil;
import cn.util.WebUtil;

/**
 * 书籍类别服务servlet
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		
		ICategoryService categoryService = new CategoryService();
		
		if("add".equals(method)) {
			Category category = WebUtil.requestFormBean(request, Category.class);
			category.setId(MakeIdUtil.makeId());
			
			boolean result = categoryService.addCategory(category);
			if(result) {
				request.setAttribute("message", "添加书籍类别成功！！！");
			}else {
				request.setAttribute("message", "添加书籍类别失败！");
			}
			
			request.getRequestDispatcher("server/message.jsp").forward(request, response);
		}else if("all".equals(method)) {
			List<Category> list = categoryService.getAllCategories();
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("server/showCategories.jsp").forward(request, response);
		}else if("delete".equals(method)) {
			String id = request.getParameter("id");
			
			boolean result = categoryService.deleteCategory(id);
			if(result) {
				request.setAttribute("message", "删除书籍类别成功！！！");
			}else {
				request.setAttribute("message", "删除书籍类别失败！");
			}
			
			request.getRequestDispatcher("server/message.jsp").forward(request, response);
		}else if("update".equals(method)) {
			String id = request.getParameter("id");
			
			Category category = WebUtil.requestFormBean(request, Category.class);
			category.setId(id);
			
			boolean result = categoryService.updateCategory(category);
			if(result) {
				request.setAttribute("message", "修改书籍类别信息成功！！！");
			}else {
				request.setAttribute("message", "修改书籍类别信息失败！");
			}
			
			request.getRequestDispatcher("server/message.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
