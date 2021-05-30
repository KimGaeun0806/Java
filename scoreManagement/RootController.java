package java2_11_practice;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable{
	
	@FXML
	private Button btnadd; //버튼 객체 
	
	@FXML
	private TableView<Student>tableview; //테이블 객체
	
	@FXML
	private Button btnbarchart; //막대그래프 버튼 객체
	
	@FXML
	private Button btndelete; //삭제 버튼 객체
	
	@FXML
	private Button btnlogout; //로그아웃 버튼 객체
	
	//객체를 담을 리스트 생성
	private ObservableList<Student>list; //ObservableList javafx용 리스트
	
	//폼이 열렸을 때
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = FXCollections.observableArrayList(); //리스트 메모리 할당
		
		TableColumn tc = tableview.getColumns().get(0); //첫 번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory("name")); //열에 값 저장(클래스 변수명)
		tc.setStyle("-fx-alignment:CENTER;");
		
		tc = tableview.getColumns().get(1); //두 번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory("kor")); //열에 값 저장(클래스 변수명)
		tc.setStyle("-fx-alignment:CENTER;");
		
		tc = tableview.getColumns().get(2); //세 번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory("math")); //열에 값 저장(클래스 변수명)
		tc.setStyle("-fx-alignment:CENTER;");
		
		tc = tableview.getColumns().get(3); //네 번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory("eng")); //열에 값 저장(클래스 변수명)
		tc.setStyle("-fx-alignment:CENTER;");
		
		tableview.setItems(list);
		
		//추가 버튼 클릭했을 때
		btnadd.setOnAction(e -> addAction(e));
		
		//막대그래프 버튼 클릭했을 때
		btnbarchart.setOnAction(e -> barChartAction(e));
		
		//테이블을 클릭했을 때
		tableview.setOnMouseClicked(e -> pieChartAction(e));
		
		//삭제버튼을 클릭했을 때
		btndelete.setOnAction(e -> {
			
			try {
				ObservableList<Student>students; //전체 학생
				ObservableList<Student>studentselected; //선택 학생
				
				students = tableview.getItems(); //테이블의 전체 값을 리스트 연결
				studentselected = tableview.getSelectionModel().getSelectedItems(); //클릭된 값 리스트 연결
				
				studentselected.forEach(students :: remove); //선택된 학생을 전체 학생 반복문에서 찾아 삭제
			}
			catch (Exception e2) {
				// TODO: handle exception
			}
		});
		
		//로그아웃 버튼을 클릭했을 때
		btnlogout.setOnAction(e -> logout(e));
		
	}
	
	//로그아웃 메소드
	public void logout(ActionEvent e) {
		
		try {
			btnlogout.getScene().getWindow().hide();
			
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("login_p.fxml"));
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			
			stage.setTitle("학생점수 관리 프로그램");
			stage.setResizable(false);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
	}//logout
	
	//막대그래프 메소드
	public void barChartAction(ActionEvent e) {
		
		try {
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("barchart.fxml"));
				//fxml 레이아웃 차트 -> 객체화
			BarChart 막대차트 = (BarChart)parent.lookup("#barchart");
			
			//1. 계열 -> 2. 계열에 추가할 리스트 생성 -> 3. 생성된 리스트를 계열 추가
			
			//국어 성적의 계열[막대] 생성
			XYChart.Series 국어계열 = new XYChart.Series();
			국어계열.setName("국어"); //계열 이름
			//리스트 내 국어성적을 국어성적 리스트 담기
			ObservableList 국어리스트 = FXCollections.observableArrayList();
			for (int i = 0; i<list.size(); i++) {
				국어리스트.add(new XYChart.Data(list.get(i).getName(), list.get(i).getKor())); 
				//XYChart.Data(x축, y축)
			}
			//국어성적 리스트 -> 막대에 추가
			국어계열.setData(국어리스트);
			//막대를 차트에 추가
			막대차트.getData().add(국어계열);
			
			
			//수학 성적의 계열[막대] 생성
			XYChart.Series 수학계열 = new XYChart.Series();
			수학계열.setName("수학");
			//리스트 내 수학성적을 수학성적리스트 담기 
			ObservableList 수학리스트 = FXCollections.observableArrayList();
			for (int i = 0; i<list.size(); i++) {
				수학리스트.add(new XYChart.Data(list.get(i).getName(), list.get(i).getName()));
			}
			//수학성적 리스트 -> 막대에 추가
			수학계열.setData(수학리스트);
			//막대를 차트에 추가
			막대차트.getData().add(수학계열);
			
			
			//영어 성적의 계열[막대] 생성
			XYChart.Series 영어계열 = new XYChart.Series();
			영어계열.setName("영어");
			//리스트 내 영어성적 영어성적리스트 담기
			ObservableList 영어리스트 = FXCollections.observableArrayList();
			for (int i = 0; i<list.size(); i++) {
				영어리스트.add(new XYChart.Data(list.get(i).getName(), list.get(i).getEng()));
			}
			영어계열.setData(영어리스트);
			막대차트.getData().add(영어계열);
			
			
			Button 닫기버튼 = (Button)parent.lookup("#btnclose");
			닫기버튼.setOnAction(e3 -> stage.close());
			
			Scene scene = new Scene(parent);
			stage.setTitle("학생별 막대그래프");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
	}//barChartAction
	
	//추가 메소드
	public void addAction(ActionEvent e) {
		
		try {
			Stage stage = new Stage(StageStyle.UTILITY);
			
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(btnadd.getScene().getWindow());
			
			Parent parent = FXMLLoader.load(getClass().getResource("form_p.fxml"));
			//parent 에서 버튼 호출
			Button btnformadd = (Button)parent.lookup("#btnformadd"); //1. 버튼 호출해서 객체화
			//저장버튼을 눌렀을 때
			btnformadd.setOnAction(e4 -> {//2. 버튼 클릭했을 때
				TextField txtname = (TextField)parent.lookup("#txtname"); //3. 각각 텍스트필드 호출 객체화
				TextField txtkor = (TextField)parent.lookup("#txtkor");
				TextField txtmath = (TextField)parent.lookup("#txtmath");
				TextField txteng = (TextField)parent.lookup("#txteng");
				
				list.add(new Student(txtname.getText(), Integer.parseInt(txtkor.getText()), Integer.parseInt(txtmath.getText()), 
						Integer.parseInt(txteng.getText())
						));
				stage.close(); //스테이지 닫기
			});
			
			//취소버튼을 눌렀을 때
			Button btnformcancel = (Button)parent.lookup("#btnformcancel");
			btnformcancel.setOnAction(e5 -> stage.close());
			
			Scene scene = new Scene(parent);
			stage.setTitle("점수입력");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
	}//addAction
	
	public void pieChartAction(MouseEvent e) {
		
		if (e.getClickCount() != 2) {//클릭수 = 2[더블클릭]가 아니면 메소드 종료
			return;
		}
		
		try {
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("piechart.fxml"));
			
			//차트를 객체화
			PieChart 원형차트 = (PieChart)parent.lookup("#piechart");
			//테이블에 클릭한 아이템을 객체에 저장 -> 클릭된 학생 저장
			Student temp = tableview.getSelectionModel().getSelectedItem();
			//원형차트에 값추가(리스트)
			원형차트.setData(FXCollections.observableArrayList(
					new PieChart.Data("국어", temp.getKor()),
					new PieChart.Data("수학", temp.getMath()),
					new PieChart.Data("영어", temp.getEng())
					));
			
			Button 닫기버튼 = (Button)parent.lookup("#btnclose");
			닫기버튼.setOnAction(e3 -> stage.close());
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("개인별 원형차트");
			stage.setResizable(false);
			stage.show();
			
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
		
		
		
	}//pieChartAction
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}//class
