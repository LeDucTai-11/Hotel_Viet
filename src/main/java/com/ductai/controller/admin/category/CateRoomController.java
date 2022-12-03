package com.ductai.controller.admin.category;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ductai.model.bean.CategoryRoomBean;
import com.ductai.model.bo.CategoryRoomBO;
import com.ductai.utils.SessionObject;
import com.ductai.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin/cateRoom"})
public class CateRoomController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if(action != null && action.equals("add")) {
			req.setAttribute("tittle", "ADD CATEGORY ROOM");
			req.setAttribute("category", new CategoryRoomBean());
			req.getRequestDispatcher("/view/admin/category/category_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT CATEGORY ROOM WITH ID : "+req.getParameter("id"));
			req.setAttribute("category", CategoryRoomBO.Instance().findByID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/category/category_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			CategoryRoomBO.Instance().delete(Integer.parseInt(id));
			List<CategoryRoomBean> data = CategoryRoomBO.Instance().findAll();
			int lastPage = data.size() % 5 == 0 ? data.size() /5 : data.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", 1);
			req.setAttribute("listCategories",findByPage(1, data));
			req.setAttribute("message", "Delete Category Room succcessfully with ID: "+id);
			req.getRequestDispatcher("/view/admin/category/categories.jsp").forward(req, resp);
		}
		else {
			String message = req.getParameter("message") != null ? req.getParameter("message") : "";
			String pageRequest = req.getParameter("page");
			Integer page = (pageRequest != null && Integer.parseInt(pageRequest) > 0) ? 
					Integer.parseInt(pageRequest) : 1;
			List<CategoryRoomBean> data = CategoryRoomBO.Instance().findAll();
			int lastPage = data.size() % 5 == 0 ? data.size() /5 : data.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", page);
			req.setAttribute("listCategories",findByPage(page, data));
			req.setAttribute("message", message);
			req.getRequestDispatcher("/view/admin/category/categories.jsp").forward(req, resp);
		}	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		CategoryRoomBean category = new CategoryRoomBean();
		String idRequest = req.getParameter("id");
		Integer id = (idRequest != null && Integer.parseInt(idRequest) > 0) ? 
				Integer.parseInt(idRequest) : -1;
		String message = "";
		SessionObject user = (SessionObject)SessionUtil.Instance().getValue(req, "USERMODEL");
		if(CategoryRoomBO.Instance().isValid(id, req.getParameter("name")) == false) {
			req.setAttribute("message", "Tên loại phòng đã bị trùng");
			if(id  > 0) {
				req.setAttribute("category", CategoryRoomBO.Instance().findByID(id));
				req.setAttribute("tittle", "EDIT CATEROOM WITH ID : "+req.getParameter("id"));
			}else {
				req.setAttribute("category", new CategoryRoomBean());
				req.setAttribute("tittle", "ADD CATEROOM");
			}
			req.getRequestDispatcher("/view/admin/category/category_form.jsp").forward(req, resp);

		}else {
			try {
				BeanUtils.populate(category, req.getParameterMap());
				if(id > 0) {
					category.setId(id);
					category.setModifiedBy(user.getFullName());
					category.setModifiedDate(new Timestamp(System.currentTimeMillis()));
					message = "Update Category Room successfully with ID: "+id;
				}else {
					category.setCreatedBy(user.getFullName());
					category.setCreatedDate(new Timestamp(System.currentTimeMillis()));
					message = "Insert Category Room successfully";
				}
				CategoryRoomBO.Instance().save(category);
				resp.sendRedirect(req.getContextPath()+"/admin/cateRoom?message="+message);
			}catch(Exception ex) {
				ex.printStackTrace();
			}	
		}
	}
	
	public List<CategoryRoomBean> findByPage(int page,List<CategoryRoomBean> data) {
		List<CategoryRoomBean> result = new ArrayList<CategoryRoomBean>();
		int startCount = (page-1)*5;
		int endCount = ((startCount + 4) < data.size()) ? (startCount + 4 ) : data.size() - 1;
		for (int i = startCount; i <= endCount;i++) {
			result.add(data.get(i));
		}
		return result;
	}
	

}
