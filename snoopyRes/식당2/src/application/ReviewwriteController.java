package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ReviewwriteController implements Initializable{
	
	@FXML
	private Button btnback5;
	
	@FXML
	private Button btnupload;
	
	@FXML
	private TextArea txtreview;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void backAction5(ActionEvent e) throws Exception{
		btnback5.getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(getClass().getResource("∏Æ∫‰√¢.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
	
	

			String c = "yy.MM.dd";
			SimpleDateFormat cal = new SimpleDateFormat(c);
			Date now = new Date();
			String nows = cal.format(now);
			
	@FXML
	public void uploadAction(ActionEvent e)throws Exception{
		Review temp = new Review(txtreview.getText(), nows);
		Dao2 dao = Dao2.getDB();
		int result = dao.setReview(temp);

		
		if (result == 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("∏Æ∫‰ ¿€º∫ øœ∑·");
			alert.showAndWait();
			
			btnupload.getScene().getWindow().hide();
			Parent parent = FXMLLoader.load(getClass().getResource("∏Æ∫‰√¢.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("∏Æ∫‰ ¿€º∫ Ω«∆–");
			alert.showAndWait();
			
			btnupload.getScene().getWindow().hide();
			Parent parent = FXMLLoader.load(getClass().getResource("∏Æ∫‰æ≤±‚√¢.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
		}
			
			
			
		
	
	}
}
