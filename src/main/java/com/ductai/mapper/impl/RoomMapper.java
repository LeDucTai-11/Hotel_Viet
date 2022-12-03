package com.ductai.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ductai.mapper.RowMapper;
import com.ductai.model.bean.RoomBean;

public class RoomMapper implements RowMapper<RoomBean> {

	@Override
	public RoomBean mapRow(ResultSet rs) {
		try {
			RoomBean result = new RoomBean();
			result.setId(rs.getInt("id"));
			result.setName(rs.getString("name"));
			result.setDescription(rs.getString("description"));
			result.setHotel_id(rs.getInt("hotel_ID"));
			result.setRoomcate_id(rs.getInt("roomcate_ID"));
			try {
				result.setHotelName(rs.getString("hotelName"));
				result.setCateName(rs.getString("cateName"));
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			result.setPrice(rs.getLong("price"));
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
