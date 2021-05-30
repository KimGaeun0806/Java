package review;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	@FXML
	private Button btnlogout;
	
	@FXML
	private Label lbllogid;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setuserid(LoginController.getinstance().userid());
	}
	
	//로그인 아이디 받는 메소드
	public void setuserid(String userid) {
		this.lbllogid.setText("안녕하세요" + userid + "님");
		
	}
	
	//로그아웃 버튼을 클릭했을 때 
	public void logoutAction(ActionEvent e) {
		lbllogid.getScene().getWindow().hide();
		
		try {
			Stage stage = new Stage();
			
			Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
			
			Scene scene = new Scene(parent);
			
			stage.setScene(scene);
			stage.show();	
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
		
	}
	
	//회원정보 버튼을 클릭했을 때
	public void memberAction(ActionEvent e) {
		try {
			Stage stage = (Stage)btnlogout.getScene().getWindow();
				//해당버튼이 존재하는 씬의 윈도우창을 스테이지에 저장 
			Parent parent = FXMLLoader.load(getClass().getResource("member.fxml"));
			
			Scene scene = new Scene(parent);
			
			stage.setScene(scene);
			stage.show();	
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
		
	
	}
	
	
}
