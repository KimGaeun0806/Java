package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReviewController implements Initializable{
	
	@FXML
	private Button btnback4;
	
	@FXML
	private Button btnwrite;
	
	@FXML
	private TableView<Review>tableview;
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Dao2 dao = Dao2.getDB(); //db ��ü ��������
    	ObservableList<Review>list = dao.getlistreview(); //db �޼ҵ� ȣ���� ��ȯ���� ����Ʈ ����
    	TableColumn tc = tableview.getColumns().get(0); //���̺��� ù ��° �� �������� 
    	tc.setCellValueFactory(new PropertyValueFactory("date"));
    	
    	tc = tableview.getColumns().get(1); //���̺��� �� ��° �� �������� 
    	tc.setCellValueFactory(new PropertyValueFactory("review"));
 
    	
    	tableview.setItems(list); //���̺� ����Ʈ �ֱ� 
    	
		
	}
	
	
	
	@FXML
	public void backAction4(ActionEvent e) throws Exception{
		btnback4.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("main2.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void writeAction(ActionEvent e) throws Exception{
		btnwrite.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("���侲��â.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	

}
