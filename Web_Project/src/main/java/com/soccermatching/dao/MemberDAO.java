

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MemberDAO {
	
	public Connection dbconn() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/soccer_matching";
			String id = "root";
			String pw = "54321";
			
			connection = DriverManager.getConnection(url, id, pw);
			System.out.println("db 立加己傍");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("db 立加角菩");
		}
		
		return connection;
		
	}
	
	
	
	
	
	
	public int memberInsert(MemberDTO dto) throws Exception {
		
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			connection = dbconn();
			
			String sql = "insert into member(id, pwd, name, gender, cphone, birthday, email) values(?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sql);
			
			
			preparedStatement.setString(1, dto.getId());
			preparedStatement.setString(2, dto.getPwd());
			preparedStatement.setString(3, dto.getName());
			preparedStatement.setInt(4, dto.getGender());
			preparedStatement.setString(5, dto.getCphone());
			preparedStatement.setString(6, dto.getBirthday());
			preparedStatement.setString(7, dto.getEmail());
			
			return preparedStatement.executeUpdate();			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("角菩");
		} finally {
			if (preparedStatement != null) preparedStatement.close();
			if (connection != null) connection.close();
			


		}
		return -1;
		
		
	}
}
