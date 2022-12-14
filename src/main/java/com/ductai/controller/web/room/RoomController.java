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

import com.ductai.model.bean.RoomBean;
import com.ductai.model.bo.HotelBO;
import com.ductai.model.bo.RoomBO;

@WebServlet(urlPatterns = {"/room"})
public class RoomController extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idHotel = req.getParameter("idHotel") == null ? "" : req.getParameter("idHotel");
		List<RoomBean> listRooms = new ArrayList<RoomBean>();
		if(idHotel != "") {
			listRooms = RoomBO.Instance().findByHotel(Integer.parseInt(idHotel));		
		}else {
			listRooms = RoomBO.Instance().findAll();
		}
		req.setAttribute("hotelName", HotelBO.Instance().findbyID(Integer.parseInt(idHotel)).getName());
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
