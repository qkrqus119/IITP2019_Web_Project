package com.soccermatching.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.soccermatching.dto.ApiInfoDTO;

public class ApiInfoDAO {
	private String driverName = "com.mysql.jdbc.Driver";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private String url = "jdbc:mysql://localhost:3306/soccer_matching";
	private String username = "newuser";
	private String password = "newuser";
	
	public ApiInfoDAO() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ApiInfoDTO read(String apiName) {
		ApiInfoDTO apiInfoDTO = new ApiInfoDTO();
		
		String sql = "select * from api_info where api_name like ?";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, apiName);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				apiInfoDTO.setApiName(resultSet.getString("api_name"));
				apiInfoDTO.setApiKey(resultSet.getString("api_key"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return apiInfoDTO;
		
	}

}
