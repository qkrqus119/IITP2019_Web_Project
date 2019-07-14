package com.soccermatching.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.soccermatching.dto.BoardPlaceInfoDTO;

public class BoardPlaceInfoDAO {
	private String driverName = "com.mysql.jdbc.Driver";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private String url = "jdbc:mysql://localhost:3306/soccer_matching";
	private String username = "newuser";
	private String password = "newuser";
	
	public BoardPlaceInfoDAO() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<BoardPlaceInfoDTO> readAll() {
		List<BoardPlaceInfoDTO> boardPlaceInfoDTOList = new ArrayList<>();
		
		String sql = "select * from board_place_info";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				BoardPlaceInfoDTO boardPlaceInfoDTO = new BoardPlaceInfoDTO();
				boardPlaceInfoDTO.setBoardNumber(resultSet.getInt("board_number"));
				boardPlaceInfoDTO.setLat(resultSet.getString("lat"));
				boardPlaceInfoDTO.setLng(resultSet.getString("lng"));
				
				boardPlaceInfoDTOList.add(boardPlaceInfoDTO);
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
		
		return boardPlaceInfoDTOList;
		
	}

}
