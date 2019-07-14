import com.soccermatching.dao.BoardDAO;

public class Main {
	
	public static void main(String[] args) {
		System.out.println(new BoardDAO().read("37.20422717257342", "37.40530852550863", "126.61898419825553", "127.56780544206099"));
	}

}
