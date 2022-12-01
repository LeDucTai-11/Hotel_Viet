package com.ductai.controller.admin.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ductai.bo.RoleBO;
import com.ductai.bo.UserBO;
import com.ductai.model.UserModel;
import com.ductai.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin/user"})
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if(action != null && action.equals("add")) {
			req.setAttribute("tittle", "ADD USER");
			req.setAttribute("listRoles", RoleBO.Instance().findAll());
			req.setAttribute("user", new UserModel());
			req.getRequestDispatcher("/view/admin/user/user_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT USER WITH ID: "+req.getParameter("id"));
			req.setAttribute("listRoles", RoleBO.Instance().findAll());
			req.setAttribute("user",UserBO.Instance().findByID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/user/user_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			UserBO.Instance().delete(Integer.parseInt(id));
			req.setAttribute("message", "Delete successfully the User with ID: "+id);
			req.setAttribute("listUsers", UserBO.Instance().findAll());
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/user/users.jsp");
			rd.forward(req, resp);
		}else {
			String idRole = req.getParameter("idRole") == null ? "" : req.getParameter("idRole");
			String message = req.getParameter("message") == null ? "" : req.getParameter("message");
			List<UserModel> listUsers = new ArrayList<UserModel>();
			if(idRole != "") {
				listUsers = UserBO.Instance().findByRole(Integer.parseInt(idRole));		
			}else {
				listUsers = UserBO.Instance().findAll();
			}
			req.setAttribute("listUsers",listUsers);
			req.setAttribute("message",message);
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/user/users.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		UserModel model = new UserModel();
		String id = req.getParameter("id") == null ? "" : req.getParameter("id");
		UserModel user = (UserModel)SessionUtil.Instance().getValue(req, "USERMODEL");
		String message = "";
		try {
			BeanUtils.populate(model, req.getParameterMap());
			if(id != "") {
				model.setId(Integer.parseInt(id));
				model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				model.setModifiedBy(user.getFullName());
				message = "Edit the USER successfully with ID: "+id;
			}else {
				model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				model.setCreatedBy(user.getFullName());
				message = "Add the USER successfully";
			}
			UserBO.Instance().saveUser(model);
			resp.sendRedirect(req.getContextPath()+"/admin/user?message="+message);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
