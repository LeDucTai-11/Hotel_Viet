package com.ductai.controller.admin.city;

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

import com.ductai.model.bean.CityModel;
import com.ductai.model.bo.CityBO;
import com.ductai.utils.SessionObject;
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
			List<CityModel> data = CityBO.Instance().findAll();
			int lastPage = data.size() % 5 == 0 ? data.size() /5 : data.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", 1);
			req.setAttribute("listCities",findByPage(1, data));
			req.setAttribute("message", "Delete succcessfully THE CITY with ID: "+id);
			req.getRequestDispatcher("/view/admin/city/cities.jsp").forward(req, resp);
		}
		else {
			String message = req.getParameter("message") != null ? req.getParameter("message") : "";
			String pageRequest = req.getParameter("page");
			Integer page = (pageRequest != null && Integer.parseInt(pageRequest) > 0) ? 
					Integer.parseInt(pageRequest) : 1;
			List<CityModel> data = CityBO.Instance().findAll();
			int lastPage = data.size() % 5 == 0 ? data.size() /5 : data.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", page);
			req.setAttribute("listCities",findByPage(page, data));
			req.setAttribute("message", message);
			req.getRequestDispatcher("/view/admin/city/cities.jsp").forward(req, resp);
		}		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		CityModel city = new CityModel();
		String idRequest = req.getParameter("id");
		Integer id = (idRequest != null && Integer.parseInt(idRequest) > 0) ? 
				Integer.parseInt(idRequest) : -1;
		String message = "";
		SessionObject user = (SessionObject)SessionUtil.Instance().getValue(req, "USERMODEL");
		if(CityBO.Instance().isValidName(id, req.getParameter("name")) == false) {
			req.setAttribute("message", "Tên thành phố đã bị trùng");
			if(id > 0) {
				req.setAttribute("city", CityBO.Instance().findByID(id));
				req.setAttribute("tittle", "EDIT CITY WITH ID : "+req.getParameter("id"));
			}else {
				req.setAttribute("city", new CityModel());
				req.setAttribute("tittle", "ADD CITY");
			}
			req.getRequestDispatcher("/view/admin/city/city_form.jsp").forward(req, resp);
		}else {
			try {
				BeanUtils.populate(city, req.getParameterMap());
				if(id > 0) {
					city.setId(id);
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
	
	public List<CityModel> findByPage(int page,List<CityModel> data) {
		List<CityModel> result = new ArrayList<CityModel>();
		int startCount = (page-1)*5;
		int endCount = ((startCount + 4) < data.size()) ? (startCount + 4 ) : data.size() - 1;
		for (int i = startCount; i <= endCount;i++) {
			result.add(data.get(i));
		}
		return result;
	}
	
	
	

}
