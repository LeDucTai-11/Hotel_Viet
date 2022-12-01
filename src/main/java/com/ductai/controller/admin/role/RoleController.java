package com.ductai.controller.admin.role;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ductai.bo.RoleBO;
import com.ductai.model.RoleModel;
import com.ductai.model.UserModel;
import com.ductai.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin/role"})
public class RoleController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if(action != null && action.equals("add")) {
			req.setAttribute("tittle", "ADD ROLE");
			req.setAttribute("role", new RoleModel());
			req.getRequestDispatcher("/view/admin/role/role_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT ROLE WITH ID : "+req.getParameter("id"));
			req.setAttribute("role", RoleBO.Instance().findByID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/role/role_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			RoleBO.Instance().delete(Integer.parseInt(id));
			req.setAttribute("listRoles", RoleBO.Instance().findAll());
			req.setAttribute("message", "Delete Role succcessfully with ID: "+id);
			req.getRequestDispatcher("/view/admin/role/roles.jsp").forward(req, resp);
		}
		else {
			String message = req.getParameter("message") != null ? req.getParameter("message") : "";
			req.setAttribute("listRoles", RoleBO.Instance().findAll());
			req.setAttribute("message", message);
			req.getRequestDispatcher("/view/admin/role/roles.jsp").forward(req, resp);
		}	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		RoleModel role = new RoleModel();
		String id = req.getParameter("id") != null ? req.getParameter("id") : "";
		String message = "";
		UserModel user = (UserModel)SessionUtil.Instance().getValue(req, "USERMODEL");
		try {
			BeanUtils.populate(role, req.getParameterMap());
			if(id != "" && Integer.parseInt(id) > 0) {
				role.setId(Integer.parseInt(id));
				role.setModifiedBy(user.getFullName());
				role.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				message = "Update ROLE successfully with ID: "+id;
			}else {
				role.setCreatedBy(user.getFullName());
				role.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				message = "Insert ROLE successfully";
			}
			RoleBO.Instance().saveRole(role);
			resp.sendRedirect(req.getContextPath()+"/admin/role?message="+message);
		}catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
	
}
