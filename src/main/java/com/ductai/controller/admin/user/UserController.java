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

import com.ductai.model.bean.UserBean;
import com.ductai.model.bo.RoleBO;
import com.ductai.model.bo.UserBO;
import com.ductai.utils.SessionObject;
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
			req.setAttribute("user", new UserBean());
			req.getRequestDispatcher("/view/admin/user/user_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT USER WITH ID: "+req.getParameter("id"));
			req.setAttribute("listRoles", RoleBO.Instance().findAll());
			req.setAttribute("user",UserBO.Instance().findByID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/user/user_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			UserBO.Instance().delete(Integer.parseInt(id));
			List<UserBean> listUsers = UserBO.Instance().findAll();
			int lastPage = listUsers.size() % 5 == 0 ? listUsers.size() /5 : listUsers.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", 1);
			req.setAttribute("listUsers",findByPage(1, listUsers));
			req.setAttribute("message", "Delete successfully the User with ID: "+id);
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/user/users.jsp");
			rd.forward(req, resp);
		}else {
			String idRole = req.getParameter("idRole") == null ? "" : req.getParameter("idRole");
			String message = req.getParameter("message") == null ? "" : req.getParameter("message");
			String pageRequest = req.getParameter("page");
			Integer page = (pageRequest != null && Integer.parseInt(pageRequest) > 0) ? 
					Integer.parseInt(pageRequest) : 1;
			List<UserBean> listUsers = new ArrayList<UserBean>();
			if(idRole != "") {
				listUsers = UserBO.Instance().findByRole(Integer.parseInt(idRole));		
			}else {
				listUsers = UserBO.Instance().findAll();
			}
			int lastPage = listUsers.size() % 5 == 0 ? listUsers.size() /5 : listUsers.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", page);
			req.setAttribute("listUsers",findByPage(page, listUsers));
			req.setAttribute("message",message);
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/user/users.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		UserBean model = new UserBean();
		String idRequest = req.getParameter("id");
		Integer id = (idRequest != null && Integer.parseInt(idRequest) > 0) ? 
				Integer.parseInt(idRequest) : -1;
		SessionObject user = (SessionObject)SessionUtil.Instance().getValue(req, "USERMODEL");
		String message = "";
		if(UserBO.Instance().isValidUserName(id, req.getParameter("userName")) == false) {
			if(id <= 0) {
				req.setAttribute("tittle", "ADD USER");
				req.setAttribute("listRoles", RoleBO.Instance().findAll());
				req.setAttribute("user", new UserBean());
			}else {
				req.setAttribute("tittle", "EDIT USER WITH ID: "+req.getParameter("id"));
				req.setAttribute("listRoles", RoleBO.Instance().findAll());
				req.setAttribute("user",UserBO.Instance().findByID(Integer.parseInt(req.getParameter("id"))));
			}
			req.setAttribute("message", "Username đã bị trùng");
			req.getRequestDispatcher("/view/admin/user/user_form.jsp").forward(req, resp);
		}else {
			try {
				BeanUtils.populate(model, req.getParameterMap());
				if(id > 0) {
					model.setId(id);
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
	
	public List<UserBean> findByPage(int page,List<UserBean> data) {
		List<UserBean> result = new ArrayList<UserBean>();
		int startCount = (page-1)*5;
		int endCount = ((startCount + 4) < data.size()) ? (startCount + 4 ) : data.size() - 1;
		for (int i = startCount; i <= endCount;i++) {
			result.add(data.get(i));
		}
		return result;
	}

}
