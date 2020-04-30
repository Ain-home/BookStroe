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
 * �鼮����
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
			//�ϴ��鼮ͼƬ
			Book book = upLoadData(request);
			
			book.setId(MakeIdUtil.makeId());
			
			boolean result = bookService.addBook(book);
			if(result) {
				request.setAttribute("message", "����鼮�ɹ�������");
			}else {
				request.setAttribute("message", "����鼮ʧ�ܣ�");
			}
			
			request.getRequestDispatcher("server/message.jsp").forward(request, response);
		}else if("all".equals(method)) {
			//�鿴�����鼮
			String currentPageCount = request.getParameter("currentPageCount"); //����������Ϊnull
			
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
			//1.��ý�����
			DiskFileItemFactory df = new DiskFileItemFactory();
			//2.�õ�������
			ServletFileUpload upLoad = new ServletFileUpload(df);
			//���ñ���
			upLoad.setHeaderEncoding("utf-8");
			//����������
			List<FileItem> list = upLoad.parseRequest(request);
			
			//����list����ȡ���������
			for(FileItem item:list) {
				if(item.isFormField()) {
					//�������ͨ���������
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					
					//��װ�������ݵ�book
					BeanUtils.setProperty(book, name, value);
				}else {
					//����ϴ����鼮ͼƬ
					//�õ��ϴ�ͼƬ·��ȫ��
					String filename = item.getName();
					//��ȡ�ļ���
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					
					//ͨ��IO�������ļ�����Ŀ
					InputStream in = item.getInputStream();
					int len = 0;
					byte[] buffer = new byte[1024];
					
					//���Ŀ��Ŀ¼�����ڣ��򴴽���Ŀ¼(��Ŀ�Ĺ���Ŀ¼)
					//����Ŀ·������  C:\Java\JavaWeb-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\�����������
//					String savepath = this.getServletContext().getRealPath("/images");
					String savepath = "C:\\Java\\JavaWeb-workspace\\�����������\\WebContent\\images";
					System.out.println(savepath);
					File file = new File(savepath);
					if(!file.exists()) {
						file.mkdir();
					}
					
					FileOutputStream out = new FileOutputStream(savepath + "\\" + filename);
                    while((len=in.read(buffer))>0){
                        out.write(buffer, 0, len);
                    }
                    
                    //�����鼮ͼƬ����
                    book.setImage(filename);
                    
                    out.close();
                    in.close();
                    
                    //�ر���ʱ�ļ�
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
