package java2_11_practice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{ 
	
	//�л� �������� ���α׷�
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("login_p.fxml"));
			//Parent: ���̾ƿ� ����ִ� [��ü] Ŭ����
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.setTitle("�л����� ���� ���α׷�");
		stage.setResizable(false);
		stage.show();
		
	}//start
	
	public static void main(String[] args) {
		launch(args);
		
	}//main

}//class
