package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	
	Connection con;
	private static Dao DB = new Dao();
	public static Dao getDB() {
		return DB;
	}
	
	public Dao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?serverTime=UTC", "root", "0806");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int setMember(Member temp) { //회원 저장 
		String SQL = "insert into restaurant values(?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, temp.getId());
			pstmt.setString(2, temp.getPw());
			pstmt.setString(3, temp.getName());
			
			pstmt.executeUpdate();
			
			return 1;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}//setmember
	
	public int login(String logid, String logpw) { //로그인 
		String SQL = "select * from restaurant where id=? and pw=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, logid);
			pstmt.setString(2, logpw);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return 1;
			}
			else {
				return 2;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public String findPw(String txtfindid, String txtfindname) {
		String SQL = "select pw from restaurant where id=? and name=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, txtfindid);
			pstmt.setString(2, txtfindname);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String pw = rs.getString("pw");
				return pw;
			}
			else {
				return "2";
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "0";
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
