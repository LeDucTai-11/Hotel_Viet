package com.ductai.controller.admin.city;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ductai.bo.CityBO;
import com.ductai.model.CityModel;
import com.ductai.model.UserModel;
import com.ductai.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin/city"})
public class CityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if(action != null && action.equals("add")) {
			req.setAttribute("tittle", "ADD CITY");
			req.setAttribute("city", new CityModel());
			req.getRequestDispatcher("/view/admin/city/city_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT CITY WITH ID : "+req.getParameter("id"));
			req.setAttribute("city", CityBO.Instance().findByID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/city/city_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			CityBO.Instance().deleteCity(Integer.parseInt(id));
			req.setAttribute("listCities", CityBO.Instance().findAll());
			req.setAttribute("message", "Delete city succcessfully with ID: "+id);
			req.getRequestDispatcher("/view/admin/city/cities.jsp").forward(req, resp);
		}
		else {
			String message = req.getParameter("message") != null ? req.getParameter("message") : "";
			req.setAttribute("listCities", CityBO.Instance().findAll());
			req.setAttribute("message", message);
			req.getRequestDispatcher("/view/admin/city/cities.jsp").forward(req, resp);
		}		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		CityModel city = new CityModel();
		String id = req.getParameter("id") != null ? req.getParameter("id") : "";
		String message = "";
		UserModel user = (UserModel)SessionUtil.Instance().getValue(req, "USERMODEL");
		try {
			BeanUtils.populate(city, req.getParameterMap());
			if(id != "" && Integer.parseInt(id) > 0) {
				city.setId(Integer.parseInt(id));
				city.setModifiedBy(user.getFullName());
				city.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				message = "Update city successfully with ID: "+id;
			}else {
				city.setCreatedBy(user.getFullName());
				city.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				message = "ADD city successfully";
			}
			CityBO.Instance().saveCity(city);
			resp.sendRedirect(req.getContextPath()+"/admin/city?message="+message);
		}catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
	
	

}
