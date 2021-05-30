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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	@FXML
	private TextField txtid;
	
	@FXML
	private PasswordField txtpw;
	
	@FXML
	private Button btnlogin2;
	
	@FXML
	private Button btnsignup;
	
	@FXML
	private Button btnfindpw;
	
	private static LoginController a;
	
	public LoginController() {
		a = this;
	}
	
	public static LoginController getA() {
		return a;
	}
	
	public String txtid() {
		return txtid.getText();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML //�α��ι�ư
	public void loginAction2(ActionEvent e) {
		Dao dao = Dao.getDB();
		
		int result = dao.login(txtid.getText(), txtpw.getText());
		
		System.out.println(result);
		
		if (result == 1) {
			try {
				btnlogin2.getScene().getWindow().hide();
				Parent parent = FXMLLoader.load(getClass().getResource("main2.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.show();
			}
			catch (Exception e5) {
				// TODO: handle exception
			}
		}
		else if (result == 2) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("���̵� Ȥ�� ��й�ȣ�� �ٸ��ϴ�");
			alert.setHeaderText("�α��� ����");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("�����ͺ��̽� ����");
			alert.setHeaderText("�α��� ����");
			alert.showAndWait();
		}
	}
	
	@FXML //ȸ������ ��ư^^
	public void signupAction(ActionEvent e)throws Exception{
		btnlogin2.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("ȸ������â.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML //��й�ȣ ã�� ��ư^^
	public void pwfindAction(ActionEvent e)throws Exception{
		btnlogin2.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("��й�ȣã��â.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML //��� ��ư
	public void backAction3(ActionEvent e) throws Exception{
		btnlogin2.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}

	

}
