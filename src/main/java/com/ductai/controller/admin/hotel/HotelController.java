package com.ductai.controller.admin.hotel;

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

import com.ductai.model.bean.HotelBean;
import com.ductai.model.bo.CityBO;
import com.ductai.model.bo.HotelBO;
import com.ductai.utils.SessionObject;
import com.ductai.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin/hotel"})
public class HotelController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if(action != null && action.equals("add")) {
			req.setAttribute("tittle", "ADD HOTEL");
			req.setAttribute("listCities", CityBO.Instance().findAll());
			req.setAttribute("hotel", new HotelBean());
			req.getRequestDispatcher("/view/admin/hotel/hotel_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT HOTEL WITH ID: "+req.getParameter("id"));
			req.setAttribute("listCities", CityBO.Instance().findAll());
			req.setAttribute("hotel",HotelBO.Instance().findbyID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/hotel/hotel_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			HotelBO.Instance().delete(Integer.parseInt(id));
			List<HotelBean> data = HotelBO.Instance().findAll();
			int lastPage = data.size() % 5 == 0 ? data.size() /5 : data.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", 1);
			req.setAttribute("listHotels",findByPage(1, data));
			req.setAttribute("message", "Delete successfully the Hotel with ID: "+id);
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/hotel/hotels.jsp");
			rd.forward(req, resp);
		}else {
			String idCity = req.getParameter("idCity");
			String message = req.getParameter("message") == null ? "" : req.getParameter("message");
			String pageRequest = req.getParameter("page");
			Integer page = (pageRequest != null && Integer.parseInt(pageRequest) > 0) ? 
					Integer.parseInt(pageRequest) : 1;
			List<HotelBean> data = new ArrayList<HotelBean>();
			if(idCity == null) {
				data = HotelBO.Instance().findAll();
			}else {
				data = HotelBO.Instance().findAll(Integer.parseInt(idCity));		
			}
			int lastPage = data.size() % 5 == 0 ? data.size() /5 : data.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", page);
			req.setAttribute("listHotels",findByPage(page, data));
			req.setAttribute("message",message);
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/hotel/hotels.jsp");
			rd.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		HotelBean hotel = new HotelBean();
		String idRequest = req.getParameter("id");
		Integer id = (idRequest != null && Integer.parseInt(idRequest) > 0) ? 
				Integer.parseInt(idRequest) : -1;
		SessionObject user = (SessionObject)SessionUtil.Instance().getValue(req, "USERMODEL");
		String message = "";
		try {
			BeanUtils.populate(hotel, req.getParameterMap());
			if(id > 0) {
				hotel.setId(id);
				hotel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				hotel.setModifiedBy(user.getFullName());
				message = "Edit the HOTEL successfully with ID: "+id;
			}else {
				hotel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				hotel.setCreatedBy(user.getFullName());
				message = "Add the HOTEL successfully";
			}
			HotelBO.Instance().saveHotel(hotel);
			resp.sendRedirect(req.getContextPath()+"/admin/hotel?message="+message);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<HotelBean> findByPage(int page,List<HotelBean> data) {
		List<HotelBean> result = new ArrayList<HotelBean>();
		int startCount = (page-1)*5;
		int endCount = ((startCount + 4) < data.size()) ? (startCount + 4 ) : data.size() - 1;
		for (int i = startCount; i <= endCount;i++) {
			result.add(data.get(i));
		}
		return result;
	}
	

}
