package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.loginJDBC;

import dao.loginDao;
import dao.loginDao;

public class loginDao {
	private static loginDao instance = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static loginDao getInstance() {
		if (instance == null)
			instance = new loginDao();
		return instance;
	}

	public boolean loginUser(String id, String pwd) {
		boolean isFind = false;
		try {
			conn = loginJDBC.getMySQlConnection();
			String sql = "select * from member where id=? and pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (id.equals(rs.getString("id")) && pwd.equals(rs.getString("pwd"))) {
					isFind = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
			if (pstmt != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
		}

		return isFind;
	}

	public loginDto getOneUser(String id) {
		loginDto dto = new loginDto();
		try {
			// Ä¿³Ø¼Ç ¿¬°á
			conn = loginJDBC.getMySQlConnection();
			// Äõ¸® ÁØºñ
			String sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			// ?¿¡ °ªÀ» ¸ÊÇÎ
			pstmt.setString(1, id);
			// Äõ¸® ½ÇÇà
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setId(rs.getString(1));
				dto.setPwd(rs.getString(2));
				dto.setName(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ÀÚ¿ø¹Ý³³
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
		return dto;
	}

	public boolean loginCheck(String id, String pwd) {
		boolean isFind = false;

		try {

			String SQL = "SELECT NAME FROM member WHERE id=? and pwd=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (id.equals(rs.getString("id")) && pwd.equals(rs.getString("pwd"))) {
					System.out.println("Ã£¾Ò´Ù.");
					isFind = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
			if (pstmt != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
		}

		return isFind;
	}
}
