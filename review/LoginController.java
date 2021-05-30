package review;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class LoginController implements Initializable{
	
	@FXML
	private TextField userid;
	
	@FXML
	private PasswordField userpw;
	
	@FXML
	private Button btnlogin;
	
	@FXML
	private Button btnsignup;
	
	@FXML
	private ImageView ingloading;
	
	@FXML
	private Button btnfind;
	
	//로그인 성공한 데이터를 다른 컨트롤러로 넘기기
		//메소드 이용
	private static LoginController instance; //해당 클래스로 임의 객체 
	
	public LoginController() { //생성자 
		instance = this; 
	}
	
	public static LoginController getinstance() {//객체 반환 메소드
		return instance;
	}
	
	public String userid() { //로그인 id 반환해주는 메소드 
		return userid.getText();
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ingloading.setVisible(false); //로딩 이미지 숨기기
	}
	
	
	@FXML //로그인 버튼을 클릭했을 때
	public void loginAction(ActionEvent e) {
		ingloading.setVisible(true); //로딩 이미지 보이기
		
		DAO dao = DAO.getDB();
		int result = dao.login(userid.getText(), userpw.getText());
		
		if (result == 1) { //로그인 성공
			//메인 스테이지 실행
			try {
				
					//최근 접속 날짜 부여 -> db
					Date date = new Date();
						//포맷 [형식 변경]
						SimpleDateFormat format = new SimpleDateFormat("yy년 MM월 dd일 hh시 mm분 ss초");
						String now = format.format(date);
						
						//db에 저장
						dao.lastdate(userid.getText(), now);
						
					
				
				btnlogin.getScene().getWindow().hide(); //해당 버튼이 존재하는 윈도우창 숨기기 
				
				Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
				
				Stage stage = new Stage();
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.show();
			}
			catch (Exception e3) {
				// TODO: handle exception
			}
			
		}
		else if (result == 2) { //로그인 실패[db 정보 없음]
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("아이디 혹은 비밀번호가 다릅니다");
			alert.setHeaderText("로그인 실패");
			alert.showAndWait();
		}
		else { //로그인 실패[db오류]
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("데이터베이스 오류\n관리자에게 문의해주세요");
			alert.setHeaderText("로그인 실패");
			alert.showAndWait();
		}
	}
	
	@FXML //회원가입 버튼을 클릭했을 때
	public void signupAction(ActionEvent e) throws Exception {
		btnlogin.getScene().getWindow().hide(); //해당 버튼이 존재하는 윈도우창 숨기기 
		
		Parent parent = FXMLLoader.load(getClass().getResource("signup.fxml"));
		
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		
	}
	
	@FXML //비밀번호 찾기 버튼을 클릭했을 때 
	public void findAction(ActionEvent e) throws Exception {
		btnlogin.getScene().getWindow().hide(); //해당 버튼이 존재하는 윈도우창 숨기기 
		
		Parent parent = FXMLLoader.load(getClass().getResource("findpw.fxml"));
		
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		
	}
	 

}
