package com.ductai.model.dao.impl;

import java.util.List;

import com.ductai.mapper.impl.HotelMapper;
import com.ductai.model.bean.HotelBean;

public class HotelDAO extends AbstractDAO<HotelBean> {
	private static HotelDAO _Instance = null;
	public static HotelDAO Instance() {
		if(_Instance == null) {
			_Instance = new HotelDAO();
		}
		return _Instance;
	}

	public List<HotelBean> findAll(Integer idCity) {
		String sql = "SELECT hotel.*,city.name AS cityName FROM hotel INNER JOIN city ON hotel.city_ID = city.id "
				+ "WHERE city_id = ? and hotel.status = true";
		return query(sql, new HotelMapper(), idCity);
	}

	public List<HotelBean> findAll() {
		String sql = "SELECT hotel.*,city.name AS cityName FROM hotel INNER JOIN city ON hotel.city_ID = city.id "
				+ "WHERE hotel.status = true";
		return query(sql, new HotelMapper());
	}
	public HotelBean findById(Integer id) {
		String sql = "Select * from hotel where id = ? and status = true";
		return query(sql, new HotelMapper(), id).get(0);
	}
	
	private void add(HotelBean hotel) {
		String sql = "Insert into hotel(name,description,city_id,address,createddate,createdby,status)"
				+ "values(?,?,?,?,?,?,?)";
		save(sql, hotel.getName(),hotel.getDescription(),hotel.getCity_id(),hotel.getAddress(),
				hotel.getCreatedDate(),hotel.getCreatedBy(),hotel.isStatus());
	}
	
	private void edit(HotelBean hotel) {
		String sql = "Update hotel set name = ?,description = ?,city_id = ?,address = ?,"
				+ "modifieddate = ?,modifiedby = ?,status = ? where id = ?";
		save(sql, hotel.getName(),hotel.getDescription(),hotel.getCity_id(),hotel.getAddress(),
				hotel.getModifiedDate(),hotel.getModifiedBy(),hotel.isStatus(),hotel.getId());
	}
	
	public void saveHotel(HotelBean hotel) {
		if(hotel.getId() <= 0) {
			add(hotel);
		}else {
			edit(hotel);
		}
	}
	
	public void delete(Integer id) {
		String sql = "update hotel set status = false where id = ?";
		save(sql, id);
	}
	

}
