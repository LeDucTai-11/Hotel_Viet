package com.ductai.model.dao.impl;

import java.util.List;

import com.ductai.mapper.impl.RoleMapper;
import com.ductai.model.bean.RoleBean;

public class RoleDAO extends AbstractDAO<RoleBean>{
	private static RoleDAO _Instance = null;
	public static RoleDAO Instance() {
		if(_Instance == null) {
			_Instance = new RoleDAO();
		}
		return _Instance;
	}

	public List<RoleBean> findAll() {
		String sql = "Select * from role where status = true";
		return query(sql, new RoleMapper());
	}
	
	public RoleBean findByID(Integer id) {
		String sql = "Select * from role where id = ? and status = true";
		List<RoleBean> roles = query(sql, new RoleMapper(), id);
		return roles.isEmpty() ? null : roles.get(0);
	}
	
	public RoleBean findByName(String name) {
		String sql = "Select * from role where name = ? and status = true";
		List<RoleBean> roles = query(sql, new RoleMapper(), name);
		return roles.isEmpty() ? null : roles.get(0);
	}
	
	private void add(RoleBean role) {
		String sql = "Insert into role(name,code,createddate,createdby,status) "
				+ "values(?,?,?,?,?)";
		save(sql, role.getName(),role.getCode(),role.getCreatedDate(),role.getCreatedBy(),role.isStatus());
	}
	
	private void edit(RoleBean role) {
		String sql = "Update role set name = ?,code = ?,modifieddate = ?,"
				+ "modifiedby = ?,status = ? where id = ?"; 
		save(sql, role.getName(),role.getCode(),role.getModifiedDate(),role.getModifiedBy(),
				role.isStatus(),role.getId());
	}
	
	public void saveRole(RoleBean role) {
		if(role.getId() <= 0) {
			add(role);
		}else {
			edit(role);
		}
	}
	
	public void delete(Integer id) {
		String sql = "update role set status = false where id - ?";
		save(sql, id);
	}

}
