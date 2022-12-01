package com.ductai.controller.web.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ductai.bo.HotelBO;
import com.ductai.bo.RoomBO;
import com.ductai.model.RoomModel;

@WebServlet(urlPatterns = {"/room"})
public class RoomController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RoomBO roomBO = new RoomBO();
	private HotelBO hotelBO = new HotelBO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idHotel = req.getParameter("idHotel") == null ? "" : req.getParameter("idHotel");
		List<RoomModel> listRooms = new ArrayList<RoomModel>();
		if(idHotel != "") {
			listRooms = this.roomBO.findByHotel(Integer.parseInt(idHotel));		
		}else {
			listRooms = this.roomBO.findAll();
		}
		req.setAttribute("hotelName", this.hotelBO.findbyID(Integer.parseInt(idHotel)).getName());
		req.setAttribute("listRooms", listRooms);
		RequestDispatcher rd = req.getRequestDispatcher("/view/web/rooms.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	

}
