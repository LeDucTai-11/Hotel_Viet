package com.ductai.model.bo;

import java.util.List;

import com.ductai.model.bean.CategoryRoomModel;
import com.ductai.model.dao.impl.CategoryRoomDAO;

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
	
	public boolean isValid(Integer id,String name) {
		CategoryRoomModel cate = CategoryRoomDAO.Instance().findByName(name);
		if(cate == null) return true;
		
		if(id <= 0) {
			if(cate != null) return false;
		}else {
			if(cate.getId() != id) return false;
		}
		return true;
	}
	
	public void save(CategoryRoomModel category) {
		CategoryRoomDAO.Instance().saveCateRoom(category);
	}
	
	public void delete(Integer id) {
		CategoryRoomDAO.Instance().delete(id);
	}
}
