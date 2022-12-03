package com.ductai.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ductai.mapper.RowMapper;
import com.ductai.model.bean.HotelBean;

public class HotelMapper implements RowMapper<HotelBean> {

	@Override
	public HotelBean mapRow(ResultSet rs) {
		try {
			HotelBean result = new HotelBean();
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
