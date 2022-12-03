package com.ductai.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ductai.mapper.RowMapper;
import com.ductai.model.bean.CategoryRoomBean;

public class CategoryRoomMapper implements RowMapper<CategoryRoomBean> {

	@Override
	public CategoryRoomBean mapRow(ResultSet rs) {
		try {
			CategoryRoomBean result = new CategoryRoomBean();
			result.setId(rs.getInt("id"));
			result.setName(rs.getString("name"));
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
		}catch(SQLException ex) {
			return null;
		}
	}
	

}
