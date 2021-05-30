package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Dao2 {

	Connection con;
	private static Dao2 DB = new Dao2();
	public static Dao2 getDB() {
		return DB;
	}
	
	public Dao2() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?serverTime=UTC", "root", "0806");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public int setReview(Review temp) { //리뷰 저장 
		
		String SQL = "insert into review values(?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, temp.getReview());
			pstmt.setString(2, temp.getDate() );
			
			pstmt.executeUpdate();
			
			return 1;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}//
	
	public ObservableList<Review>getlistreview(){
			
			//임시 리스트
			ObservableList<Review>list = FXCollections.observableArrayList();
			String SQL = "SELECT * FROM review";
			try {
				PreparedStatement pstmt = con.prepareStatement(SQL);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Review temp = new Review();
					temp.setDate(rs.getString(1));
					temp.setReview(rs.getString(2));
					
					list.add(temp);
				}
				return list;
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			return list; 
		}
	
}
