package com.ductai.bo;

import java.util.List;

import com.ductai.dao.impl.RoleDAO;
import com.ductai.model.RoleModel;

public class RoleBO {
	private static RoleBO _Instance;
	public static RoleBO Instance() {
		if(_Instance == null) {
			_Instance = new RoleBO();
		}
		return _Instance;
	}
	
	public List<RoleModel> findAll() {
		return RoleDAO.Instance().findAll();
	}
	
	public RoleModel findByID(Integer id) {
		return RoleDAO.Instance().findByID(id);
	}
	
	public void saveRole(RoleModel role) {
		RoleDAO.Instance().saveRole(role);
	}
	
	public void delete(Integer id) {
		UserBO.Instance().findByRole(id).forEach(user -> {
			UserBO.Instance().delete(user.getId());
		});
		RoleDAO.Instance().delete(id);
	}
}
