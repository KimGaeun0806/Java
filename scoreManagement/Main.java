package java2_11_practice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{ 
	
	//학생 점수관리 프로그램
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("login_p.fxml"));
			//Parent: 레이아웃 담아주는 [객체] 클래스
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.setTitle("학생점수 관리 프로그램");
		stage.setResizable(false);
		stage.show();
		
	}//start
	
	public static void main(String[] args) {
		launch(args);
		
	}//main

}//class
