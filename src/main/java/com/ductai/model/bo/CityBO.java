package com.ductai.model.bo;

import java.util.List;

import com.ductai.model.bean.CityBean;
import com.ductai.model.dao.impl.CityDAO;

public class CityBO {
	private static CityBO _Instance = null;
	public static CityBO Instance() {
		if(_Instance == null) {
			_Instance = new CityBO();
		}
		return _Instance;
	}
	
	public List<CityBean> findAll() {
		return CityDAO.Instance().findAll();
	}
	
	public CityBean findByID(Integer id) {
		return CityDAO.Instance().findByID(id);
	}
	
	public boolean isValidName(Integer id,String name) {
		CityBean city = CityDAO.Instance().findByName(name);
		if(city == null) return true;
		
		if(id <= 0) {
			if(city != null) return false;
		}else {
			if(city.getId() != id) return false;
		}
		return true;
	}
	
	public void saveCity(CityBean city) {
		CityDAO.Instance().saveCity(city);
	}
	public void deleteCity(Integer id) {
		HotelBO.Instance().findAll(id).forEach(hotel -> {
			HotelBO.Instance().delete(hotel.getId());
		});
		CityDAO.Instance().delete(id);
	}
}
