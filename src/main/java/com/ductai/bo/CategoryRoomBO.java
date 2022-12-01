package com.ductai.bo;

import java.util.List;

import com.ductai.dao.impl.CategoryRoomDAO;
import com.ductai.model.CategoryRoomModel;

public class CategoryRoomBO {
	private static CategoryRoomBO _Instance;
	public static CategoryRoomBO Instance() {
		if(_Instance == null) {
			_Instance = new CategoryRoomBO();
		}
		return _Instance;
	}
	
	public List<CategoryRoomModel> findAll() {
		return CategoryRoomDAO.Instance().findAll();
	}
	
	public CategoryRoomModel findByID(Integer id) {
		return CategoryRoomDAO.Instance().findByID(id);
	}
	
	public void save(CategoryRoomModel category) {
		CategoryRoomDAO.Instance().saveCateRoom(category);
	}
	
	public void delete(Integer id) {
		CategoryRoomDAO.Instance().delete(id);
	}
}
