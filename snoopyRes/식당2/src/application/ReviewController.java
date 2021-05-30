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
		Dao2 dao = Dao2.getDB(); //db 객체 가져오기
    	ObservableList<Review>list = dao.getlistreview(); //db 메소드 호출의 반환값을 리스트 저장
    	TableColumn tc = tableview.getColumns().get(0); //테이블의 첫 번째 열 가져오기 
    	tc.setCellValueFactory(new PropertyValueFactory("date"));
    	
    	tc = tableview.getColumns().get(1); //테이블의 두 번째 열 가져오기 
    	tc.setCellValueFactory(new PropertyValueFactory("review"));
 
    	
    	tableview.setItems(list); //테이블에 리스트 넣기 
    	
		
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
		Parent parent = FXMLLoader.load(getClass().getResource("리뷰쓰기창.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	

}
