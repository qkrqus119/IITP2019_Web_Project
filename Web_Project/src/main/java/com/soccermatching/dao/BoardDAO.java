package com.soccermatching.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.soccermatching.dto.BoardDTO;
import com.soccermatching.dto.BoardPlaceInfoDTO;

public class BoardDAO {

	private String driverName = "com.mysql.jdbc.Driver";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private String url = "jdbc:mysql://localhost:3306/soccer_matching";
	private String username = "newuser";
	private String password = "newuser";

	public BoardDAO() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<BoardDTO> readAll() {
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
String sql = "select * from board";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setAddress(resultSet.getString("address"));
				boardDTO.setAuthor(resultSet.getInt("author"));
				boardDTO.setContent(resultSet.getString("content"));
				boardDTO.setGameDate(resultSet.getDate("game_date"));
				boardDTO.setGameGenderType(resultSet.getInt("game_gender_type"));
				boardDTO.setGameStartTime(resultSet.getTime("game_start_time").toLocalTime());
				boardDTO.setGameType(resultSet.getString("game_type"));
				boardDTO.setNumber(resultSet.getInt("number"));
				boardDTO.setPlaceName(resultSet.getString("place_name"));
				boardDTO.setRegisterDate(resultSet.getDate("register_date"));
				
				boardDTOList.add(boardDTO);
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
		
		return boardDTOList;
	}

	public BoardDTO read(int number) {
		BoardDTO boardDTO = new BoardDTO();

		String sql = "select * from board where number = ?";

		try {
			connection = DriverManager.getConnection(url, username, password);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				boardDTO.setAddress(resultSet.getString("address"));
				boardDTO.setAuthor(resultSet.getInt("author"));
				boardDTO.setContent(resultSet.getString("content"));
				boardDTO.setGameDate(resultSet.getDate("game_date"));
				boardDTO.setGameGenderType(resultSet.getInt("game_gender_type"));
				boardDTO.setGameStartTime(resultSet.getTime("game_start_time").toLocalTime());
				boardDTO.setGameType(resultSet.getString("game_type"));
				boardDTO.setNumber(resultSet.getInt("number"));
				boardDTO.setPlaceName(resultSet.getString("place_name"));
				boardDTO.setRegisterDate(resultSet.getDate("register_date"));
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

		return boardDTO;
	}

}
