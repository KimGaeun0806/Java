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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class SignupController implements Initializable{
	
	@FXML
	private TextField txtsignupid;
	
	@FXML
	private PasswordField txtsignuppw;
	
	@FXML
	private TextField txtsignupname;
	
	@FXML
	private Button btnsignup2;
	
	@FXML
	private Button btnback;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void signupAction2(ActionEvent e)throws Exception{
		if (txtsignupid.getText().equals("") || txtsignuppw.getText().equals("") || txtsignupname.getText().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("��� �׸��� �Է����ּ���");
			alert.setHeaderText("���� ����");
			alert.showAndWait();
			return;
		}
		else {
			Member temp = new Member(txtsignupid.getText(), txtsignuppw.getText(), txtsignupname.getText());
			
			Dao dao = Dao.getDB();
			int result = dao.setMember(temp);
			
			if (result == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("���� ����!");
				alert.showAndWait();
				
				btnsignup2.getScene().getWindow().hide();
				Parent parent = FXMLLoader.load(getClass().getResource("�α���â.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.show();
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("������ ���̵� ��й�ȣ�� �����մϴ�");
				alert.setHeaderText("���� ����");
				alert.showAndWait();
				
				btnsignup2.getScene().getWindow().hide();
				Parent parent = FXMLLoader.load(getClass().getResource("ȸ������â.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.show();
				
			}
			
			
		}
	}
	
	@FXML //��� ��ư
	public void backAction3(ActionEvent e) throws Exception{
		btnsignup2.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("�α���â.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
	

}
