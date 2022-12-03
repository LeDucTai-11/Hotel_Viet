package com.ductai.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ductai.mapper.RowMapper;
import com.ductai.model.bean.RoleBean;

public class RoleMapper implements RowMapper<RoleBean>{

	@Override
	public RoleBean mapRow(ResultSet rs) {
		try {
			RoleBean result = new RoleBean();
			result.setId(rs.getInt("id"));
			result.setName(rs.getString("name"));
			result.setCode(rs.getString("code"));
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
