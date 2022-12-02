package com.ductai.controller.admin.role;

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

import com.ductai.model.bean.RoleModel;
import com.ductai.model.bo.RoleBO;
import com.ductai.utils.SessionObject;
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
			List<RoleModel> data = RoleBO.Instance().findAll();
			int lastPage = data.size() % 5 == 0 ? data.size() /5 : data.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", 1);
			req.setAttribute("listRoles",findByPage(1, data));
			req.setAttribute("message", "Delete Role succcessfully with ID: "+id);
			req.getRequestDispatcher("/view/admin/role/roles.jsp").forward(req, resp);
		}
		else {
			String message = req.getParameter("message") != null ? req.getParameter("message") : "";
			String pageRequest = req.getParameter("page");
			Integer page = (pageRequest != null && Integer.parseInt(pageRequest) > 0) ? 
					Integer.parseInt(pageRequest) : 1;
			List<RoleModel> data = RoleBO.Instance().findAll();
			int lastPage = data.size() % 5 == 0 ? data.size() /5 : data.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", page);
			req.setAttribute("listRoles",findByPage(page, data));
			req.setAttribute("message", message);
			req.getRequestDispatcher("/view/admin/role/roles.jsp").forward(req, resp);
		}	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		RoleModel role = new RoleModel();
		String idRequest = req.getParameter("id");
		Integer id = (idRequest != null && Integer.parseInt(idRequest) > 0) ? 
				Integer.parseInt(idRequest) : -1;
		String message = "";
		SessionObject user = (SessionObject)SessionUtil.Instance().getValue(req, "USERMODEL");
		if(RoleBO.Instance().isValid(id, req.getParameter("name")) == false) {
			if(id > 0) {
				req.setAttribute("role", RoleBO.Instance().findByID(id));
				req.setAttribute("tittle", "EDIT ROLE WITH ID : "+req.getParameter("id"));
			}else {
				req.setAttribute("role", new RoleModel());
				req.setAttribute("tittle", "ADD ROLE");
			}
			req.getRequestDispatcher("/view/admin/role/role_form.jsp").forward(req, resp);

		}else {
			try {
				BeanUtils.populate(role, req.getParameterMap());
				if(id > 0) {
					role.setId(id);
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
	
	public List<RoleModel> findByPage(int page,List<RoleModel> data) {
		List<RoleModel> result = new ArrayList<RoleModel>();
		int startCount = (page-1)*5;
		int endCount = ((startCount + 4) < data.size()) ? (startCount + 4 ) : data.size() - 1;
		for (int i = startCount; i <= endCount;i++) {
			result.add(data.get(i));
		}
		return result;
	}
	
}
