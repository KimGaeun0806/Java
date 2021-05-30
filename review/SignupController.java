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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SignupController implements Initializable{
	
	@FXML
	private TextField signupid;
	
	@FXML
	private PasswordField signuppw;
	
	@FXML
	private Button btnsignup;
	
	@FXML
	private Button btnback;
	
	@FXML
	private TextField signupname;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML //뒤로가기 버튼을 클릭했을 때
	public void back(ActionEvent e) throws Exception {
		btnsignup.getScene().getWindow().hide(); //해당 버튼이 존재하는 윈도우창 숨기기 
		
		Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
		
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		
	}
	
	@FXML //가입버튼을 클릭했을 때 
	public void signupAction(ActionEvent e) throws Exception {
		
		if (signupid.getText().equals("") || signuppw.getText().equals("") || signupname.getText().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("세 항목 모두 입력해주십시오");
			alert.setTitle("경고");
			alert.setHeaderText("가입실패");
			
			alert.showAndWait();
			
			return;
		}
		else {
			Member temp = new Member(signupid.getText(), signuppw.getText(), signupname.getText());
			
			DAO dao = DAO.getDB();
			int result = dao.setmember(temp);
			
			if (result == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("가입 성공\n확인 클릭 시 로그인창으로 이동합니다");
				alert.setHeaderText("가입 성공");
				alert.showAndWait();
			}
			else {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("가입 실패\n동일한 아이디가 존재합니다");
				alert.setHeaderText("가입 실패");
				alert.showAndWait();
			}
			
			//가입 후 로그인창으로
			btnsignup.getScene().getWindow().hide(); //해당 버튼이 존재하는 윈도우창 숨기기 
			
			Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
			
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
		}
		
		
	}
	 

}
