package com.ductai.bo;

import java.util.List;

import com.ductai.dao.impl.CityDAO;
import com.ductai.model.CityModel;

public class CityBO {
	private static CityBO _Instance = null;
	public static CityBO Instance() {
		if(_Instance == null) {
			_Instance = new CityBO();
		}
		return _Instance;
	}
	
	public List<CityModel> findAll() {
		return CityDAO.Instance().findAll();
	}
	
	public CityModel findByID(Integer id) {
		return CityDAO.Instance().findByID(id);
	}
	
	public void saveCity(CityModel city) {
		CityDAO.Instance().saveCity(city);
	}
	public void deleteCity(Integer id) {
		HotelBO.Instance().findAll(id).forEach(hotel -> {
			HotelBO.Instance().delete(hotel.getId());
		});
		CityDAO.Instance().delete(id);
	}
}
