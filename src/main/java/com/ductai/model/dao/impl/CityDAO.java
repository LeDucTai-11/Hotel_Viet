package com.ductai.model.dao.impl;

import java.util.List;

import com.ductai.mapper.impl.CityMapper;
import com.ductai.model.bean.CityModel;

public class CityDAO extends AbstractDAO<CityModel>{
	
	private static CityDAO _Instance = null;
	public static CityDAO Instance() {
		if(_Instance == null) {
			_Instance = new CityDAO();
		}
		return _Instance;
	}

	public List<CityModel> findAll() {
		String sql = "Select * from city where status = true";
		return query(sql, new CityMapper());
	}

	public CityModel findByID(Integer id) {
		String sql = "Select * from city where id = ? and status = true";
		List<CityModel> cities = query(sql, new CityMapper(), id);
		return cities.isEmpty() ? null : cities.get(0);
	}
	
	public CityModel findByName(String name) {
		String sql = "Select * from city where name = ? and status = true";
		List<CityModel> cities = query(sql, new CityMapper(), name);
		return cities.isEmpty() ? null : cities.get(0);
	}
	
	private void add(CityModel city) {
		String sql = "Insert into city(name,createddate,createdby,status) "
				+ "values(?,?,?,?)";
		save(sql, city.getName(),city.getCreatedDate(),city.getCreatedBy(),city.isStatus());
	}
	private void edit(CityModel city) {
		String sql = "Update city set name = ?,modifieddate = ?,"
				+ "modifiedby = ?,status = ? where id = ?";
		save(sql, city.getName(),city.getModifiedDate(),city.getModifiedBy(),city.isStatus(),city.getId());
	}
	
	public void saveCity(CityModel city) {
		if(city.getId() <= 0 ) {
			add(city);
		}else {
			edit(city);
		}
	}
	
	public void delete(Integer id) {
		String sql = "Update city set status = false where id = ?";
		save(sql, id);
	}
}
