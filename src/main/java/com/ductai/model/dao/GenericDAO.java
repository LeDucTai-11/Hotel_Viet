package com.ductai.model.dao;

import java.util.List;

import com.ductai.mapper.RowMapper;

public interface GenericDAO<T> {
	List<T> query(String sql, RowMapper<T> rowMapper , Object ...params);
	void save (String sql, Object... parameters);
}
