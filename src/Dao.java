import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Dao {
	String url = "jdbc:mysql://localhost:3306/board?serverTimezone=UTC";
	String id = "sbsst";
	String pw = "sbs123414";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	List<Article> getAllArticles() {
		List<Article> articles = null;
		
		try {
			String sql = "SELECT * FROM article";

			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			articles = new ArrayList<>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String body = rs.getString("body");
				String nickname = rs.getString("nickname");
				int hit = rs.getInt("hit");
				
				Article article = new Article(id, title, body, nickname, hit);
				
				articles.add(article);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		
		return articles;
	}
	
	
	void insertArticle(String title, String body) {
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO article SET title = '" + title + "', body = '" + body + "', nickname = '유저99', regDate = '20200817'";

			stmt.executeUpdate(sql);
		}
		catch (SQLException e) {	
			e.printStackTrace();
		} 
		finally {
			close();
		}
	}
	
	
	public void printArticle(int id) {
		String sql = "SELECT * FROM article WHERE id = " + id;
		
		conn = getConnection();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				System.out.println("번호 : " + rs.getInt("id"));
				System.out.println("제목 : " + rs.getString("title"));
				System.out.println("내용 : " + rs.getString("body"));
				System.out.println("작성자 : " + rs.getString("nickname"));
				System.out.println("조회수 : " + rs.getInt("hit"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateArticle(int id, String title, String body) {
		String sql = "UPDATE article SET title = '" + title + "', body = '" + body + "'WHERE id = " +id;
		System.out.println(sql);
		
		conn = getConnection();
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	
	public void deleteArticle(int id) {
		String sql = "DELETE FROM article WHERE id = " + id;
		
		conn = getConnection();
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	
	void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
