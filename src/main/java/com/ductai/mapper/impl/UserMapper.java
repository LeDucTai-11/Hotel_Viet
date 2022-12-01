package com.ductai.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ductai.mapper.RowMapper;
import com.ductai.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel result = new UserModel();
			result.setId(rs.getInt("id"));
			result.setFullName(rs.getString("fullname"));
			result.setUserName(rs.getString("username"));
			result.setPassword(rs.getString("password"));
			result.setAddress(rs.getString("address"));
			result.setRole_id(rs.getInt("role_id"));
			try {
				result.setRoleName(rs.getString("roleName"));
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			result.setStatus(rs.getBoolean("status"));
			if(rs.getTimestamp("createddate") != null) {
				result.setCreatedDate(rs.getTimestamp("createddate"));
			}
			if(rs.getTimestamp("modifieddate") != null) {
				result.setModifiedDate(rs.getTimestamp("modifieddate"));;
			}
			if(rs.getString("createdby") != null) {
				result.setCreatedBy(rs.getString("createdby"));;
			}
			if(rs.getString("modifiedby") != null) {
				result.setModifiedBy(rs.getString("modifiedby"));
			}
			return result;
		}catch(SQLException e) {
			return null;
		}
	}

}
