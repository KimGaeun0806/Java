package review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class DAO {
	
	Connection conn; //인터페이스 선언
	
	private static DAO DB = new DAO(); //객체 선언 
	
	public static DAO getDB() { //객체 반환하는 메소드 
		return DB;
	}
	
	//생성자: 객체 생성시 초기값 
	public DAO() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/review?serverTime=UTC", "root", "0806");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//회원 저장하는 메소드
	public int setmember(Member temp) {
		
		String SQL = "insert into member values(?,?,?,?)";
			//SQL 삽입문법: insert into 테이블명 values([필드1]값1, [필드2]값2, [필드3]값2)
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
				// PreparedStatement[I]: SQL 질의어 제어/관리 가능 
			pstmt.setString(1, temp.getId());
			pstmt.setString(2, temp.getPw());
			pstmt.setString(3, temp.getName());
			pstmt.setString(4,"0");
			
			pstmt.executeUpdate(); //해당 SQL문 실행
			
			return 1; //SQL 삽입 성공 
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0; //실패: 아이디 중복 //DB 오류
		
	}
	//회원 로그인 메소드
	public int login(String logid, String logpw) {
		String SQL = "select * from member where id=? and pw =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, logid);
			pstmt.setString(2, logpw);
			
			ResultSet rs = pstmt.executeQuery();
				//ResultSet [I]: 쿼리결과값 연동
					//.next(): 쿼리 다음값[쿼리 첫번째 값은: null]
			
			if (rs.next()) {
				return 1; //로그인 성공 
			}
			else {
				return 2; //로그인 실패[아이디/비밀번호가 없음]
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0; //db오류
		
		
	}
	
	//회원 비밀번호 찾기 메소드
	public String getpw(String findid, String findname) {
		String SQL = "select pw from member where id=? and name=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); 
			pstmt.setString(1, findid);
			pstmt.setString(2, findname);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String pw = rs.getString("pw");
									//getstring("필드명"): 해당 결과의 필드의 값 반환 
				
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
	
	//해당 id에 회원정보 찾는 메소드 
	public Member getmember(String logid) {
		Member temp = new Member();
		String SQL = "SELECT * from member where id = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, logid);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				temp.setId(rs.getString(1));
				temp.setPw(rs.getString(2));
				temp.setName(rs.getString(3));
				return temp;
			}
			else {
				return temp;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return temp;
	}
	
	//로그인된 id에 회원정보 삭제 메소드
	public int delmember(String logid) {
		//사용범위 반환값[return/void] 메소드명(인수)
		String SQL = "delete from member where id = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, logid);
			pstmt.executeUpdate();
			
			return 1;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
		
	}
	
	//모든 회원목록 출력하는 메소드
	public ArrayList<Member>listmember(){
		ArrayList<Member>temp = new ArrayList<>();
		
		String SQL = "select * from member";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery(); 
			//쿼리 결과 다수
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getString(1));
				member.setPw(rs.getString(2));
				member.setName(rs.getString(3));
				
				temp.add(member); //쿼리결과를 하나씩 리스트에 담기
				
			}
			
			return temp;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return temp; 
	}
	
	//모든 회원 목록 출력 메소드
	public ObservableList<Member>getlistmember(){
		
		//임시 리스트
		ObservableList<Member>list = FXCollections.observableArrayList();
		String SQL = "SELECT * FROM member";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member temp = new Member();
				temp.setId(rs.getString(1));
				temp.setPw(rs.getString(2));
				temp.setName(rs.getString(3));
				temp.setLastdate(rs.getString(4));
				
				list.add(temp);
			}
			return list;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return list; 
	}
	
	//접속날짜 db 저장 메소드
	public void lastdate(String userid, String now) {
		
		String SQL  = "update member set lastdate = ? where id = ?";
		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, now);
			pstmt.setString(2, userid);
			
			
			pstmt.executeUpdate(); 
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//로그인된 id의 회원정보 수정 메소드
	public int updatemember(String cpw, String cname, String userid) {
		String SQL = "update member set pw = ?, name = ? where id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, cpw);
			pstmt.setString(2, cname);
			pstmt.setString(3, userid);
			
			pstmt.executeUpdate();
			return 1;
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0; 
	}
	
	

}
