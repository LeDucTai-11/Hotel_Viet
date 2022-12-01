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

import com.ductai.bo.CityBO;
import com.ductai.bo.HotelBO;
import com.ductai.model.HotelModel;
import com.ductai.model.UserModel;
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
			req.setAttribute("hotel", new HotelModel());
			req.getRequestDispatcher("/view/admin/hotel/hotel_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT HOTEL WITH ID: "+req.getParameter("id"));
			req.setAttribute("listCities", CityBO.Instance().findAll());
			req.setAttribute("hotel",HotelBO.Instance().findbyID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/hotel/hotel_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			HotelBO.Instance().delete(Integer.parseInt(id));
			req.setAttribute("message", "Delete successfully the Hotel with ID: "+id);
			req.setAttribute("listHotels", HotelBO.Instance().findAll());
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/hotel/hotels.jsp");
			rd.forward(req, resp);
		}else {
			String idCity = req.getParameter("idCity");
			String message = req.getParameter("message") == null ? "" : req.getParameter("message");
			List<HotelModel> listHotels = new ArrayList<HotelModel>();
			if(idCity == null) {
				listHotels = HotelBO.Instance().findAll();
			}else {
				listHotels = HotelBO.Instance().findAll(Integer.parseInt(idCity));		
			}
			req.setAttribute("listHotels",listHotels);
			req.setAttribute("message",message);
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/hotel/hotels.jsp");
			rd.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		HotelModel hotel = new HotelModel();
		String id = req.getParameter("id") == null ? "" : req.getParameter("id");
		UserModel user = (UserModel)SessionUtil.Instance().getValue(req, "USERMODEL");
		String message = "";
		try {
			BeanUtils.populate(hotel, req.getParameterMap());
			if(id != "") {
				hotel.setId(Integer.parseInt(id));
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
	

}
