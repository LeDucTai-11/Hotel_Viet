package com.ductai.model.dao.impl;

import java.util.List;

import com.ductai.mapper.impl.UserMapper;
import com.ductai.model.bean.UserModel;

public class UserDAO extends AbstractDAO<UserModel>  {
	
	private static UserDAO _Instance = null;
	public static UserDAO Instance() {
		if(_Instance == null) {
			_Instance = new UserDAO();
		}
		return _Instance;
	}
	
	public List<UserModel> findAll() {
		String sql = "SELECT user.*,role.name AS roleName FROM user INNER JOIN role ON user.role_id = role.id "
				+ "WHERE user.status = true";
		return query(sql, new UserMapper());
	}

	
	public List<UserModel> findByRoleID(Integer idRole) {
		String sql = "SELECT user.*,role.name AS roleName FROM user INNER JOIN role ON user.role_id = role.id "
				+ "WHERE role_id = ? AND user.status = true";
		return query(sql, new UserMapper(), idRole);
	}
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password) {
		String sql = "SELECT user.*,role.name AS roleName FROM user INNER JOIN role ON user.role_id = role.id "
				+ "WHERE username = ? and password = ? and user.status = true";
		List<UserModel> users = query(sql, new UserMapper(), userName, password);
		return users.isEmpty() ? null : users.get(0);
	}
	
	public UserModel findByUserName(String userName) {
		String sql = "SELECT user.*,role.name AS roleName FROM user INNER JOIN role ON user.role_id = role.id "
				+ "WHERE username = ? and user.status = true";
		List<UserModel> users = query(sql, new UserMapper(), userName);
		return users.isEmpty() ? null : users.get(0);
	}
	
	public UserModel findByID(Integer id) {
		String sql = "Select * from user where id = ? and status = true";
		List<UserModel> users = query(sql, new UserMapper(), id);
		return users.isEmpty() ? null : users.get(0);
	}
	
	private void add(UserModel user) {
		String sql = "Insert into user(role_id,fullname,username,password,address,createddate,createdby,status)"
				+ "values(?,?,?,?,?,?,?,?)";
		save(sql, user.getRole_id(),user.getFullName(),user.getUserName(),user.getPassword(),
				user.getAddress(),user.getCreatedDate(),user.getCreatedBy(),user.isStatus());
	}
	
	private void edit(UserModel user) {
		String sql = "Update user set fullname = ?,username = ?,password = ?,address = ?,"
				+ "role_id = ?,modifieddate = ?,modifiedby = ?,status = ? where id = ?"; 
		save(sql, user.getFullName(),user.getUserName(),user.getPassword(),
				user.getAddress(),user.getRole_id(),user.getModifiedDate(),user.getModifiedBy(),
				user.isStatus(),user.getId());
	}
	
	public void saveUser(UserModel user) {
		if(user.getId() <= 0) {
			add(user);
		}else {
			edit(user);
		}
	}
	
	public void delete(Integer id) {
		String sql = "Update user set status = false where id = ?";
		save(sql, id);
	}
	

}
