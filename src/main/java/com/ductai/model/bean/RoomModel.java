package com.ductai.model.bean;

public class RoomModel extends AbstractModel {
	private String name;
	private String description;
	private Integer hotel_id;
	private Integer roomcate_id;
	private String hotelName;
	private String cateName;
	private Long price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Integer getRoomcate_id() {
		return roomcate_id;
	}
	public void setRoomcate_id(Integer roomcate_id) {
		this.roomcate_id = roomcate_id;
	}
	
	
	
	
	
	
}
