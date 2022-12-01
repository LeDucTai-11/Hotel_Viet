package com.ductai.bo;

import java.util.List;

import com.ductai.dao.impl.UserDAO;
import com.ductai.model.UserModel;

public class UserBO {
	private static UserBO _Instance = null;
	public static UserBO Instance() {
		if(_Instance == null) {
			_Instance = new UserBO();
		}
		return _Instance;
	}
	
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password) {
		return UserDAO.Instance().findByUserNameAndPasswordAndStatus(userName, password);
	}
	
	public List<UserModel> findAll() {
		return UserDAO.Instance().findAll();
	}
	public UserModel findByID(Integer id) {
		return UserDAO.Instance().findByID(id);
	}
	
	public List<UserModel> findByRole(Integer idRole) {
		return UserDAO.Instance().findByRoleID(idRole);
	}
	
	public void saveUser(UserModel user) {
		UserDAO.Instance().saveUser(user);
	}
	
	public void delete(Integer id) {
		UserDAO.Instance().delete(id);
	}
	
}
