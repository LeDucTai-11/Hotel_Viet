package com.ductai.controller.web.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ductai.bo.CityBO;
import com.ductai.bo.HotelBO;
import com.ductai.model.HotelModel;

@WebServlet(urlPatterns = {"/hotel"})
public class HotelController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private HotelBO hotelBO = new HotelBO();
	private CityBO cityBO = new CityBO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idCity = req.getParameter("idCity") == null ? "" : req.getParameter("idCity");
		List<HotelModel> listHotels = new ArrayList<HotelModel>();
		if(idCity != "") {
			listHotels = this.hotelBO.findAll(Integer.parseInt(idCity));		
		}else {
			listHotels = this.hotelBO.findAll();
		}
		req.setAttribute("cityName", this.cityBO.findByID(Integer.parseInt(idCity)).getName());
		req.setAttribute("listHotels",listHotels);
		RequestDispatcher rd = req.getRequestDispatcher("/view/web/hotels.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

}