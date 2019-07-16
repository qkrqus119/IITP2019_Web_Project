

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberdao;
	
	
	
    public void init() {
    	memberdao = new MemberDAO();
    	
    	
    }
	
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿호출");
		response.setCharacterEncoding("UTF-8");
		String context = request.getContextPath();

		MemberDAO dao = new MemberDAO();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		int gender = request.getIntHeader("gender");
			if(gender != 0) {
				gender = 1;
			}
		String cphone = request.getParameter("cphone");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		
		MemberDTO dto = new MemberDTO(id, pwd, name, gender, cphone, birthday, email);
		
		try {
			dao.memberInsert(dto);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("서블릿 호출 실패");
		}
		response.sendRedirect("main.jsp");
	}

}
