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
			alert.setContentText("�Է��Ͻ� ������ ������ �������� �ʽ��ϴ�");
			alert.setHeaderText("��й�ȣ ã�� ����");
			alert.showAndWait();
		}
		else if(result.equals("0")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("�����ͺ��̽� ����");
			alert.setHeaderText("��й�ȣ ã�� ����");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText(txtfindname.getText() + "���� ��й�ȣ�� " + 
			result + "�Դϴ�.");
			alert.setHeaderText("��й�ȣ ã�� ����");
			alert.showAndWait();
		}
				
		
				
				
	}//findAction
	
	@FXML //��� ��ư
	public void backAction2(ActionEvent e)throws Exception{
		btnfind.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("�α���â.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}

}
