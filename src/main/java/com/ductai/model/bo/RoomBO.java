package com.ductai.model.bo;

import java.util.List;

import com.ductai.model.bean.RoomModel;
import com.ductai.model.dao.impl.RoomDAO;

public class RoomBO {
	
	private static RoomBO _Instance = null;
	public static RoomBO Instance() {
		if(_Instance == null) {
			_Instance = new RoomBO();
		}
		return _Instance;
	}
	
	public List<RoomModel> findAll(){
		return RoomDAO.Instance().findAll();
	}
	
	public List<RoomModel> findByHotel(Integer idHotel){
		return RoomDAO.Instance().findByHotel(idHotel);
	}
	
	public List<RoomModel> findByCate(Integer idCate) {
		return RoomDAO.Instance().findByCate(idCate);
	}
	
	public RoomModel findByID(Integer id) {
		return RoomDAO.Instance().findByID(id);
	}
	
	public void saveRoom(RoomModel model) {
		RoomDAO.Instance().saveRoom(model);
	}
	
	public void delete(Integer id) {
		RoomDAO.Instance().delete(id);
	}
	
}
