package com.ductai.controller.admin.category;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ductai.bo.CategoryRoomBO;
import com.ductai.model.CategoryRoomModel;
import com.ductai.model.UserModel;
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
			req.setAttribute("category", new CategoryRoomModel());
			req.getRequestDispatcher("/view/admin/category/category_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT CATEGORY ROOM WITH ID : "+req.getParameter("id"));
			req.setAttribute("category", CategoryRoomBO.Instance().findByID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/category/category_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			CategoryRoomBO.Instance().delete(Integer.parseInt(id));
			req.setAttribute("listCategories", CategoryRoomBO.Instance().findAll());
			req.setAttribute("message", "Delete Category Room succcessfully with ID: "+id);
			req.getRequestDispatcher("/view/admin/category/categories.jsp").forward(req, resp);
		}
		else {
			String message = req.getParameter("message") != null ? req.getParameter("message") : "";
			req.setAttribute("listCategories", CategoryRoomBO.Instance().findAll());
			req.setAttribute("message", message);
			req.getRequestDispatcher("/view/admin/category/categories.jsp").forward(req, resp);
		}	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		CategoryRoomModel category = new CategoryRoomModel();
		String id = req.getParameter("id") != null ? req.getParameter("id") : "";
		String message = "";
		UserModel user = (UserModel)SessionUtil.Instance().getValue(req, "USERMODEL");
		try {
			BeanUtils.populate(category, req.getParameterMap());
			if(id != "" && Integer.parseInt(id) > 0) {
				category.setId(Integer.parseInt(id));
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
