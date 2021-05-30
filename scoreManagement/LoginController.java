package java2_11_practice;

import java.net.URL;
import java.util.ResourceBundle;

import application.login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	@FXML
	private Button btnlogin;
	
	@FXML
	private Button btnsignup;
	
	@FXML
	private TextField id;
	
	@FXML
	private PasswordField pw;
	
	private ObservableList<Member>memberlist;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		memberlist = FXCollections.observableArrayList(); //리스트 메모리 할당
		
		btnlogin.setOnAction(e -> login(e));
		btnsignup.setOnAction(e -> signup(e));
		
	}
	
	//로그인 메소드
	public void login (ActionEvent e) {
		
		try {
			for (int i = 0; i<memberlist.size(); i++) {
				if (id.getText().equals(memberlist.get(i).getid()) && pw.getText().equals(memberlist.get(i).getpw())) {
					btnlogin.getScene().getWindow().hide(); //로그인 성공시 해당 스테이지 닫기
					
					Stage stage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("root_p.fxml"));
						//Parent: 레이아웃 담아주는 [객체] 클래스
					
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("학생 점수 관리");
					stage.setResizable(false);
					stage.show();
				}
			}
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
	}//login
	
	//회원가입 메소드
	public void signup(ActionEvent e) {
		
		try {
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("sign_p.fxml"));
			Button btnsignup = (Button)parent.lookup("#btnsignup");
			
			btnsignup.setOnAction(e4 -> {
				TextField txtid = (TextField)parent.lookup("#txtid");
				TextField txtpw = (TextField)parent.lookup("txtpw");
				
				memberlist.add(new Member(
						txtid.getText(), txtpw.getText()
						));
				
				stage.close();
			});
			
			//취소버튼을 눌렀을 때
			Button btnformcancel = (Button)parent.lookup("#btncancel");
			btnformcancel.setOnAction(e5 -> {stage.close();});
			
			//Parent:레이아웃 담아주는 [객체] 클래스
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("회원가입");
			stage.setResizable(false);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
	}//signup
	

}//class
