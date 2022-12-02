package com.ductai.controller.admin.room;

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

import com.ductai.model.bean.RoomModel;
import com.ductai.model.bo.CategoryRoomBO;
import com.ductai.model.bo.HotelBO;
import com.ductai.model.bo.RoomBO;
import com.ductai.utils.SessionObject;
import com.ductai.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin/room"})
public class RoomController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		if(action != null && action.equals("add")) {
			req.setAttribute("tittle", "ADD ROOM");
			req.setAttribute("listHotels", HotelBO.Instance().findAll());
			req.setAttribute("listCates", CategoryRoomBO.Instance().findAll());
			req.setAttribute("room", new RoomModel());
			req.getRequestDispatcher("/view/admin/room/room_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("edit")) {
			req.setAttribute("tittle", "EDIT ROOM WITH ID: "+req.getParameter("id"));
			req.setAttribute("listHotels", HotelBO.Instance().findAll());
			req.setAttribute("listCates", CategoryRoomBO.Instance().findAll());
			req.setAttribute("room",RoomBO.Instance().findByID(Integer.parseInt(req.getParameter("id"))));
			req.getRequestDispatcher("/view/admin/room/room_form.jsp").forward(req, resp);
		}else if(action != null && action.equals("delete")) {
			String id = req.getParameter("id");
			RoomBO.Instance().delete(Integer.parseInt(id));
			List<RoomModel> listRooms = RoomBO.Instance().findAll();
			int lastPage = listRooms.size() % 5 == 0 ? listRooms.size() /5 : listRooms.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", 1);
			req.setAttribute("listRooms", findByPage(1, listRooms));
			req.setAttribute("message", "Delete successfully the Room with ID: "+id);
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/room/rooms.jsp");
			rd.forward(req, resp);
		}else {
			String idHotel = req.getParameter("idHotel");
			String idCategory = req.getParameter("idCategory");
			String pageRequest = req.getParameter("page");
			Integer page = (pageRequest != null && Integer.parseInt(pageRequest) > 0) ? 
					Integer.parseInt(pageRequest) : 1;
			List<RoomModel> listRooms = new ArrayList<RoomModel>();
			if(idHotel != null) {
				listRooms = RoomBO.Instance().findByHotel(Integer.parseInt(idHotel));		
			}else if(idCategory != null) {
				listRooms = RoomBO.Instance().findByCate(Integer.parseInt(idCategory));
			}
			else {
				listRooms = RoomBO.Instance().findAll();
			}
			int lastPage = listRooms.size() % 5 == 0 ? listRooms.size() /5 : listRooms.size() / 5 +1;
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("currentPage", page);
			req.setAttribute("listRooms", findByPage(page, listRooms));
			req.setAttribute("message", req.getParameter("message"));
			RequestDispatcher rd = req.getRequestDispatcher("/view/admin/room/rooms.jsp");
			rd.forward(req, resp);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		RoomModel model = new RoomModel();
		String idRequest = req.getParameter("id");
		Integer id = (idRequest != null && Integer.parseInt(idRequest) > 0) ? 
				Integer.parseInt(idRequest) : -1;
		SessionObject user = (SessionObject)SessionUtil.Instance().getValue(req, "USERMODEL");
		String message = "";
		try {
			BeanUtils.populate(model, req.getParameterMap());
			if(id > 0) {
				model.setId(id);
				model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				model.setModifiedBy(user.getFullName());
				message = "Edit the ROOM successfully with ID: "+id;
			}else {
				model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				model.setCreatedBy(user.getFullName());
				message = "Add the ROOM successfully";
			}
			RoomBO.Instance().saveRoom(model);
			resp.sendRedirect(req.getContextPath()+"/admin/room?message="+message);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public List<RoomModel> findByPage(int page,List<RoomModel> data) {
		List<RoomModel> result = new ArrayList<RoomModel>();
		int startCount = (page-1)*5;
		int endCount = ((startCount + 4) < data.size()) ? (startCount + 4 ) : data.size() - 1;
		for (int i = startCount; i <= endCount;i++) {
			result.add(data.get(i));
		}
		return result;
	}
	

}
