package com.ductai.model.bo;

import java.util.List;

import com.ductai.model.bean.HotelModel;
import com.ductai.model.dao.impl.HotelDAO;

public class HotelBO {
	private static HotelBO _Instance = null;
	public static HotelBO Instance() {
		if(_Instance == null) {
			_Instance = new HotelBO();
		}
		return _Instance;
	}
	
	public List<HotelModel> findAll(){
		return HotelDAO.Instance().findAll();
	}
	
	public List<HotelModel> findAll(Integer idCity) {
		return HotelDAO.Instance().findAll(idCity);
	}
	public HotelModel findbyID(Integer id) {
		return HotelDAO.Instance().findById(id);
	}
	
	public void saveHotel(HotelModel hotel) {
		HotelDAO.Instance().saveHotel(hotel);
	}
	
	public void delete(Integer id) {
		RoomBO.Instance().findByHotel(id).forEach(room -> {
			RoomBO.Instance().delete(room.getId());
		});
		HotelDAO.Instance().delete(id);
	}
	

}
