package com.ductai.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ductai.mapper.RowMapper;
import com.ductai.model.dao.GenericDAO;

public class AbstractDAO<T> implements GenericDAO<T> {
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hotel";
			String user = "root";
			String password = "";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	private void setParameter(PreparedStatement statement,Object ...parameters) {
		try {
			for(int i = 0; i < parameters.length;i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if(parameter instanceof Integer) {
					statement.setInt(index, (Integer)parameter);
				}else if(parameter instanceof String) {
					statement.setString(index, (String)parameter);
				}else if(parameter instanceof Boolean) {
					statement.setBoolean(index, (Boolean)parameter);
				}else if(parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp)parameter);
				}else if(parameter instanceof Long) {
					statement.setLong(index, (Long)parameter);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<T> query(String sql,RowMapper<T> rowMapper, Object... params) {
		List<T> results = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameter(statement, params);
			rs = statement.executeQuery();
			while(rs.next()) {
				results.add(rowMapper.mapRow(rs));
			}
			return results;
		}catch(SQLException e) {
			return null;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(rs != null){
					rs.close();
				}
			}catch(SQLException e) {
				return null;
			}
		}
	}
	
	@Override
	public void save(String sql, Object... parameters) {
		Connection cnn = null;
		PreparedStatement statement = null;
		try {
			cnn = getConnection();
			cnn.setAutoCommit(false);
			statement = cnn.prepareStatement(sql);
			setParameter(statement, parameters);
			statement.executeUpdate();
			cnn.commit();
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			if(cnn != null) {
				try {
					cnn.rollback();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}finally {
			try {
				if(cnn != null) {
					cnn.close();
				}
				if(statement != null) {
					statement.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
