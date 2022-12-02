package com.ductai.model.dao.impl;

import java.util.List;

import com.ductai.mapper.impl.CategoryRoomMapper;
import com.ductai.model.bean.CategoryRoomModel;

public class CategoryRoomDAO extends AbstractDAO<CategoryRoomModel>{
	private static CategoryRoomDAO _Instance = null;
	public static CategoryRoomDAO Instance() {
		if(_Instance == null) {
			_Instance = new CategoryRoomDAO();
		}
		return _Instance;
	}
	
	public List<CategoryRoomModel> findAll() {
		String sql = "Select * from roomcate where status = true";
		return query(sql, new CategoryRoomMapper());
	}
	
	public CategoryRoomModel findByID(Integer id) {
		String sql = "Select * from roomcate where id = ? and status = true";
		List<CategoryRoomModel> categories = query(sql, new CategoryRoomMapper(), id);
		return categories.isEmpty() ? null : categories.get(0);
	}
	
	public CategoryRoomModel findByName(String name) {
		String sql = "Select * from roomcate where name = ? and status = true";
		List<CategoryRoomModel> categories = query(sql, new CategoryRoomMapper(), name);
		return categories.isEmpty() ? null : categories.get(0);
	}
	
	private void add(CategoryRoomModel category) {
		String sql = "Insert into roomcate(name,createddate,createdby,status) "
				+ "values(?,?,?,?)";
		save(sql, category.getName(),category.getCreatedDate(),category.getCreatedBy(),category.isStatus());
	}
	
	private void edit(CategoryRoomModel category) {
		String sql = "Update roomcate set name = ?,modifieddate = ?,"
				+ "modifiedby = ?,status = ? where id = ?";
		save(sql, category.getName(),category.getModifiedDate(),category.getModifiedBy(),
				category.isStatus(),category.getId());
	}
	
	public void saveCateRoom(CategoryRoomModel category) {
		if(category.getId() <= 0) {
			add(category);
		}else {
			edit(category);
		}
	}
	
	public void delete(Integer id) {
		String sql = "update roomcate set status = false where id = ?";
		save(sql, id);
	}
}
