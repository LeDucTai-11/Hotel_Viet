package com.ductai.model.bo;

import java.util.List;

import com.ductai.model.bean.RoomBean;
import com.ductai.model.dao.impl.RoomDAO;

public class RoomBO {
	
	private static RoomBO _Instance = null;
	public static RoomBO Instance() {
		if(_Instance == null) {
			_Instance = new RoomBO();
		}
		return _Instance;
	}
	
	public List<RoomBean> findAll(){
		return RoomDAO.Instance().findAll();
	}
	
	public List<RoomBean> findByHotel(Integer idHotel){
		return RoomDAO.Instance().findByHotel(idHotel);
	}
	
	public List<RoomBean> findByCate(Integer idCate) {
		return RoomDAO.Instance().findByCate(idCate);
	}
	
	public RoomBean findByID(Integer id) {
		return RoomDAO.Instance().findByID(id);
	}
	
	public void saveRoom(RoomBean model) {
		RoomDAO.Instance().saveRoom(model);
	}
	
	public void delete(Integer id) {
		RoomDAO.Instance().delete(id);
	}
	
}
