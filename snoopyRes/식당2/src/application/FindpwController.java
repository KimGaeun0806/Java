package application;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FindpwController implements Initializable{
	
	@FXML
	private TextField txtfindid;
	
	@FXML
	private TextField txtfindname;
	
	@FXML
	private Button btnfind;
	
	@FXML
	private Button btnback2;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void findAction(ActionEvent e) {
		Dao dao = Dao.getDB();
		String result = dao.findPw(txtfindid.getText(), txtfindname.getText());
		
		if (result.equals("2")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("입력하신 정보의 계정이 존재하지 않습니다");
			alert.setHeaderText("비밀번호 찾기 실패");
			alert.showAndWait();
		}
		else if(result.equals("0")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("데이터베이스 오류");
			alert.setHeaderText("비밀번호 찾기 실패");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(txtfindname.getText() + "님의 비밀번호는 " + 
			result + "입니다.");
			alert.setHeaderText("비밀번호 찾기 성공");
			alert.showAndWait();
		}
				
		
				
				
	}//findAction
	
	@FXML //취소 버튼
	public void backAction2(ActionEvent e)throws Exception{
		btnfind.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("로그인창.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}

}
