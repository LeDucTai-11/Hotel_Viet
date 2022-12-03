package com.ductai.model.bo;

import java.util.List;

import com.ductai.model.bean.UserBean;
import com.ductai.model.dao.impl.UserDAO;

public class UserBO {
	private static UserBO _Instance = null;
	public static UserBO Instance() {
		if(_Instance == null) {
			_Instance = new UserBO();
		}
		return _Instance;
	}
	
	public UserBean findByUserNameAndPasswordAndStatus(String userName, String password) {
		return UserDAO.Instance().findByUserNameAndPasswordAndStatus(userName, password);
	}
	
	public UserBean findByUserName(String userName) {
		return UserDAO.Instance().findByUserName(userName);
	}
	
	public List<UserBean> findAll() {
		return UserDAO.Instance().findAll();
	}
	public UserBean findByID(Integer id) {
		return UserDAO.Instance().findByID(id);
	}
	
	public boolean isValidUserName(Integer id,String userName) {
		UserBean foundUser = UserDAO.Instance().findByUserName(userName);
		if(foundUser == null) return true;
		
		if(id <= 0) {
			if(foundUser != null) return false;
		}else {
			if(foundUser.getId() != id) return false;
		}
		return true;
	}
	
	public List<UserBean> findByRole(Integer idRole) {
		return UserDAO.Instance().findByRoleID(idRole);
	}
	
	public void saveUser(UserBean user) {
		UserDAO.Instance().saveUser(user);
	}
	
	public void delete(Integer id) {
		UserDAO.Instance().delete(id);
	}
	
}
