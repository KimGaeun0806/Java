package java2_11_practice;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
	
	//필드
	SimpleStringProperty name;
	SimpleIntegerProperty kor;
	SimpleIntegerProperty math;
	SimpleIntegerProperty eng;
	
	//생성자
	public Student() {
		this.name = new SimpleStringProperty();
		this.kor = new SimpleIntegerProperty();
		this.math = new SimpleIntegerProperty();
		this.eng = new SimpleIntegerProperty();
	}
	
	//생성자
	public Student(String name, int kor, int math, int eng) {
		this.name = new SimpleStringProperty(name);
		this.kor = new SimpleIntegerProperty(kor);
		this.math = new SimpleIntegerProperty(math);
		this.eng = new SimpleIntegerProperty(eng);
	}
	
	//필드 반환 = get / 저장 = set 메소드
	//오른쪽클릭 -> 소스
	
	public String getName() {
		return name.get();
	}
	
	public void setName(SimpleStringProperty name) {
		this.name = name;
	}
	
	public int getKor() {
		return kor.get();
	}
	
	public void setKor(SimpleIntegerProperty kor) {
		this.kor = kor;
	}
	
	public int getMath() {
		return math.get();
	}
	
	public void setMath(SimpleIntegerProperty math) {
		this.math = math;
	}
	
	public int getEng() {
		return eng.get();
	}
	
	public void setEng(SimpleIntegerProperty eng) {
		this.eng = eng;
	}
	
}
