package com.ductai.model.dao.impl;

import java.util.List;

import com.ductai.mapper.impl.RoomMapper;
import com.ductai.model.bean.RoomBean;

public class RoomDAO extends AbstractDAO<RoomBean>  {
	
	//Singleton
	private static RoomDAO _Instance = null;
	public static RoomDAO Instance() {
		if(_Instance == null) {
			_Instance = new RoomDAO();
		}
		return _Instance;
	}
	
	public List<RoomBean> findByHotel(Integer idHotel) {
		String sql = "SELECT room.*,hotel.name AS hotelName,roomcate.name AS cateName "
				+ "FROM (room INNER JOIN hotel ON room.hotel_ID = hotel.id) "
				+ "INNER JOIN roomcate ON room.roomcate_ID = roomcate.id "
				+ "WHERE hotel_ID = ? AND room.status = true";
		return query(sql, new RoomMapper(), idHotel);
	}
	
	public List<RoomBean> findByCate(Integer idCate) {
		String sql = "SELECT room.*,hotel.name AS hotelName,roomcate.name AS cateName "
				+ "FROM (room INNER JOIN hotel ON room.hotel_ID = hotel.id) "
				+ "INNER JOIN roomcate ON room.roomcate_ID = roomcate.id "
				+ "WHERE roomcate_ID = ? AND room.status = true";
		return query(sql, new RoomMapper(), idCate);
	}

	public List<RoomBean> findAll() {
		String sql = "SELECT room.*,hotel.name AS hotelName,roomcate.name AS cateName "
				+ "FROM (room INNER JOIN hotel ON room.hotel_ID = hotel.id) "
				+ "INNER JOIN roomcate ON room.roomcate_ID = roomcate.id WHERE room.status = true";
		return query(sql, new RoomMapper());
	}
	
	public RoomBean findByID(Integer id) {
		String sql = "SELECT room.*,hotel.name AS hotelName,roomcate.name AS cateName "
				+ "FROM (room INNER JOIN hotel ON room.hotel_ID = hotel.id) "
				+ "INNER JOIN roomcate ON room.roomcate_ID = roomcate.id "
				+ "WHERE room.id = ? AND room.status = true";
		List<RoomBean> rooms = query(sql, new RoomMapper(), id);
		return rooms.isEmpty() ? null : rooms.get(0);
	}
	
	private void add(RoomBean room) {
		String sql = "Insert into room(name,description,hotel_ID,roomcate_ID,price,"
				+ "createddate,createdby,status) values(?,?,?,?,?,?,?,?)";
		save(sql, room.getName(),room.getDescription(),room.getHotel_id(),room.getRoomcate_id(),
				room.getPrice(),room.getCreatedDate(),room.getCreatedBy(),room.isStatus());
	}
	
	private void edit(RoomBean model) {
		String sql = "update room set name = ?,description = ?,hotel_ID = ?,roomcate_ID = ?,"
				+ "price = ?,modifieddate = ?,modifiedby = ?, status = ? where id = ?";
		save(sql, model.getName(),model.getDescription(),model.getHotel_id(),model.getRoomcate_id(),
				model.getPrice(),model.getModifiedDate(),model.getModifiedBy(),model.isStatus(),
				model.getId());
	}
	
	public void saveRoom(RoomBean model) {
		if(model.getId() <= 0) {
			add(model);
		}else {
			edit(model);
		}
	}
	
	public void delete(Integer id) {
		String sql = "Update room set status = false where id = ?";
		save(sql, id);
	}
	
	
	
}
