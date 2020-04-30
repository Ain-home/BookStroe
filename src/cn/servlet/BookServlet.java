package cn.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.entity.Book;
import cn.entity.Page;
import cn.service.IBookService;
import cn.service.imp.BookService;
import cn.util.MakeIdUtil;

/**
 * 书籍操作
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		
		IBookService bookService = new BookService();
		
		if("add".equals(method)) {
			//上传书籍图片
			Book book = upLoadData(request);
			
			book.setId(MakeIdUtil.makeId());
			
			boolean result = bookService.addBook(book);
			if(result) {
				request.setAttribute("message", "添加书籍成功！！！");
			}else {
				request.setAttribute("message", "添加书籍失败！");
			}
			
			request.getRequestDispatcher("server/message.jsp").forward(request, response);
		}else if("all".equals(method)) {
			//查看所有书籍
			String currentPageCount = request.getParameter("currentPageCount"); //若不存在则为null
			
			Page page = bookService.getPageBooks(currentPageCount);
			page.setUrl(request.getRequestURI());
			
			request.setAttribute("page", page);
			request.getRequestDispatcher("server/showBooks.jsp").forward(request, response);
		}
	}

	private Book upLoadData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Book book = new Book();
		try {
			//1.获得解析器
			DiskFileItemFactory df = new DiskFileItemFactory();
			//2.得到解析器
			ServletFileUpload upLoad = new ServletFileUpload(df);
			//设置编码
			upLoad.setHeaderEncoding("utf-8");
			//解析表单数据
			List<FileItem> list = upLoad.parseRequest(request);
			
			//遍历list，获取输入表单数据
			for(FileItem item:list) {
				if(item.isFormField()) {
					//如果是普通输入表单内容
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					
					//封装输入数据到book
					BeanUtils.setProperty(book, name, value);
				}else {
					//获得上传的书籍图片
					//得到上传图片路径全名
					String filename = item.getName();
					//截取文件名
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					
					//通过IO流保存文件至项目
					InputStream in = item.getInputStream();
					int len = 0;
					byte[] buffer = new byte[1024];
					
					//如果目标目录不存在，则创建该目录(项目的工作目录)
					//本项目路径工作  C:\Java\JavaWeb-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\简易网上书城
//					String savepath = this.getServletContext().getRealPath("/images");
					String savepath = "C:\\Java\\JavaWeb-workspace\\简易网上书城\\WebContent\\images";
					System.out.println(savepath);
					File file = new File(savepath);
					if(!file.exists()) {
						file.mkdir();
					}
					
					FileOutputStream out = new FileOutputStream(savepath + "\\" + filename);
                    while((len=in.read(buffer))>0){
                        out.write(buffer, 0, len);
                    }
                    
                    //设置书籍图片属性
                    book.setImage(filename);
                    
                    out.close();
                    in.close();
                    
                    //关闭临时文件
                    item.delete();
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
