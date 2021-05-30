package review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class DAO {
	
	Connection conn; //�������̽� ����
	
	private static DAO DB = new DAO(); //��ü ���� 
	
	public static DAO getDB() { //��ü ��ȯ�ϴ� �޼ҵ� 
		return DB;
	}
	
	//������: ��ü ������ �ʱⰪ 
	public DAO() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/review?serverTime=UTC", "root", "0806");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//ȸ�� �����ϴ� �޼ҵ�
	public int setmember(Member temp) {
		
		String SQL = "insert into member values(?,?,?,?)";
			//SQL ���Թ���: insert into ���̺�� values([�ʵ�1]��1, [�ʵ�2]��2, [�ʵ�3]��2)
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
				// PreparedStatement[I]: SQL ���Ǿ� ����/���� ���� 
			pstmt.setString(1, temp.getId());
			pstmt.setString(2, temp.getPw());
			pstmt.setString(3, temp.getName());
			pstmt.setString(4,"0");
			
			pstmt.executeUpdate(); //�ش� SQL�� ����
			
			return 1; //SQL ���� ���� 
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0; //����: ���̵� �ߺ� //DB ����
		
	}
	//ȸ�� �α��� �޼ҵ�
	public int login(String logid, String logpw) {
		String SQL = "select * from member where id=? and pw =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, logid);
			pstmt.setString(2, logpw);
			
			ResultSet rs = pstmt.executeQuery();
				//ResultSet [I]: ��������� ����
					//.next(): ���� ������[���� ù��° ����: null]
			
			if (rs.next()) {
				return 1; //�α��� ���� 
			}
			else {
				return 2; //�α��� ����[���̵�/��й�ȣ�� ����]
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return 0; //db����
		
		
	}
	
	//ȸ�� ��й�ȣ ã�� �޼ҵ�
	public String getpw(String findid, String findname) {
		String SQL = "select pw from member where id=? and name=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); 
			pstmt.setString(1, findid);
			pstmt.setString(2, findname);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String pw = rs.getString("pw");
									//getstring("�ʵ��"): �ش� ����� �ʵ��� �� ��ȯ 
				
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
	
	//�ش� id�� ȸ������ ã�� �޼ҵ� 
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
	
	//�α��ε� id�� ȸ������ ���� �޼ҵ�
	public int delmember(String logid) {
		//������ ��ȯ��[return/void] �޼ҵ��(�μ�)
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
	
	//��� ȸ����� ����ϴ� �޼ҵ�
	public ArrayList<Member>listmember(){
		ArrayList<Member>temp = new ArrayList<>();
		
		String SQL = "select * from member";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery(); 
			//���� ��� �ټ�
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getString(1));
				member.setPw(rs.getString(2));
				member.setName(rs.getString(3));
				
				temp.add(member); //��������� �ϳ��� ����Ʈ�� ���
				
			}
			
			return temp;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return temp; 
	}
	
	//��� ȸ�� ��� ��� �޼ҵ�
	public ObservableList<Member>getlistmember(){
		
		//�ӽ� ����Ʈ
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
	
	//���ӳ�¥ db ���� �޼ҵ�
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
	
	//�α��ε� id�� ȸ������ ���� �޼ҵ�
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
