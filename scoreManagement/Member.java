package java2_11_practice;

import javafx.beans.property.SimpleStringProperty;

public class Member {
	
	SimpleStringProperty id;
	SimpleStringProperty pw;
	
	public Member() {
		
	}
	
	public Member(String id, String pw) {
		this.id = new SimpleStringProperty(id);
		this.pw = new SimpleStringProperty(pw);
	}
	
	public String getid() {
		return id.get();
	}
	
	public void setid(SimpleStringProperty id) {
		this.id = id;
	}
	
	public String getpw() {
		return pw.get();
	}
	
	public void setpw(SimpleStringProperty pw) {
		this.pw = pw;
	}

}
