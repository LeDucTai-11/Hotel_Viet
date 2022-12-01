package com.ductai.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ductai.mapper.RowMapper;
import com.ductai.model.HotelModel;

public class HotelMapper implements RowMapper<HotelModel> {

	@Override
	public HotelModel mapRow(ResultSet rs) {
		try {
			HotelModel result = new HotelModel();
			result.setId(rs.getInt("id"));
			result.setName(rs.getString("name"));
			result.setDescription(rs.getString("description"));
			result.setAddress(rs.getString("address"));
			result.setCity_id(rs.getInt("city_ID"));
			try {
				result.setCityName(rs.getString("cityName"));
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
