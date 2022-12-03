package com.ductai.model.bo;

import java.util.List;

import com.ductai.model.bean.RoleBean;
import com.ductai.model.dao.impl.RoleDAO;

public class RoleBO {
	private static RoleBO _Instance;
	public static RoleBO Instance() {
		if(_Instance == null) {
			_Instance = new RoleBO();
		}
		return _Instance;
	}
	
	public List<RoleBean> findAll() {
		return RoleDAO.Instance().findAll();
	}
	
	public RoleBean findByID(Integer id) {
		return RoleDAO.Instance().findByID(id);
	}
	
	public boolean isValid(Integer id,String name) {
		RoleBean role = RoleDAO.Instance().findByName(name);
		if(role == null) return true;
		
		if(id <= 0) {
			if(role != null) return false;
		}else {
			if(role.getId() != id) return false;
		}
		return true;
	}
	
	public void saveRole(RoleBean role) {
		RoleDAO.Instance().saveRole(role);
	}
	
	public void delete(Integer id) {
		UserBO.Instance().findByRole(id).forEach(user -> {
			UserBO.Instance().delete(user.getId());
		});
		RoleDAO.Instance().delete(id);
	}
}
