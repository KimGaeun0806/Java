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
	private Button btnadd; //��ư ��ü 
	
	@FXML
	private TableView<Student>tableview; //���̺� ��ü
	
	@FXML
	private Button btnbarchart; //����׷��� ��ư ��ü
	
	@FXML
	private Button btndelete; //���� ��ư ��ü
	
	@FXML
	private Button btnlogout; //�α׾ƿ� ��ư ��ü
	
	//��ü�� ���� ����Ʈ ����
	private ObservableList<Student>list; //ObservableList javafx�� ����Ʈ
	
	//���� ������ ��
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = FXCollections.observableArrayList(); //����Ʈ �޸� �Ҵ�
		
		TableColumn tc = tableview.getColumns().get(0); //ù ��° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory("name")); //���� �� ����(Ŭ���� ������)
		tc.setStyle("-fx-alignment:CENTER;");
		
		tc = tableview.getColumns().get(1); //�� ��° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory("kor")); //���� �� ����(Ŭ���� ������)
		tc.setStyle("-fx-alignment:CENTER;");
		
		tc = tableview.getColumns().get(2); //�� ��° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory("math")); //���� �� ����(Ŭ���� ������)
		tc.setStyle("-fx-alignment:CENTER;");
		
		tc = tableview.getColumns().get(3); //�� ��° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory("eng")); //���� �� ����(Ŭ���� ������)
		tc.setStyle("-fx-alignment:CENTER;");
		
		tableview.setItems(list);
		
		//�߰� ��ư Ŭ������ ��
		btnadd.setOnAction(e -> addAction(e));
		
		//����׷��� ��ư Ŭ������ ��
		btnbarchart.setOnAction(e -> barChartAction(e));
		
		//���̺��� Ŭ������ ��
		tableview.setOnMouseClicked(e -> pieChartAction(e));
		
		//������ư�� Ŭ������ ��
		btndelete.setOnAction(e -> {
			
			try {
				ObservableList<Student>students; //��ü �л�
				ObservableList<Student>studentselected; //���� �л�
				
				students = tableview.getItems(); //���̺��� ��ü ���� ����Ʈ ����
				studentselected = tableview.getSelectionModel().getSelectedItems(); //Ŭ���� �� ����Ʈ ����
				
				studentselected.forEach(students :: remove); //���õ� �л��� ��ü �л� �ݺ������� ã�� ����
			}
			catch (Exception e2) {
				// TODO: handle exception
			}
		});
		
		//�α׾ƿ� ��ư�� Ŭ������ ��
		btnlogout.setOnAction(e -> logout(e));
		
	}
	
	//�α׾ƿ� �޼ҵ�
	public void logout(ActionEvent e) {
		
		try {
			btnlogout.getScene().getWindow().hide();
			
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("login_p.fxml"));
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			
			stage.setTitle("�л����� ���� ���α׷�");
			stage.setResizable(false);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
	}//logout
	
	//����׷��� �޼ҵ�
	public void barChartAction(ActionEvent e) {
		
		try {
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("barchart.fxml"));
				//fxml ���̾ƿ� ��Ʈ -> ��üȭ
			BarChart ������Ʈ = (BarChart)parent.lookup("#barchart");
			
			//1. �迭 -> 2. �迭�� �߰��� ����Ʈ ���� -> 3. ������ ����Ʈ�� �迭 �߰�
			
			//���� ������ �迭[����] ����
			XYChart.Series ����迭 = new XYChart.Series();
			����迭.setName("����"); //�迭 �̸�
			//����Ʈ �� ������� ����� ����Ʈ ���
			ObservableList �����Ʈ = FXCollections.observableArrayList();
			for (int i = 0; i<list.size(); i++) {
				�����Ʈ.add(new XYChart.Data(list.get(i).getName(), list.get(i).getKor())); 
				//XYChart.Data(x��, y��)
			}
			//����� ����Ʈ -> ���뿡 �߰�
			����迭.setData(�����Ʈ);
			//���븦 ��Ʈ�� �߰�
			������Ʈ.getData().add(����迭);
			
			
			//���� ������ �迭[����] ����
			XYChart.Series ���а迭 = new XYChart.Series();
			���а迭.setName("����");
			//����Ʈ �� ���м����� ���м�������Ʈ ��� 
			ObservableList ���и���Ʈ = FXCollections.observableArrayList();
			for (int i = 0; i<list.size(); i++) {
				���и���Ʈ.add(new XYChart.Data(list.get(i).getName(), list.get(i).getName()));
			}
			//���м��� ����Ʈ -> ���뿡 �߰�
			���а迭.setData(���и���Ʈ);
			//���븦 ��Ʈ�� �߰�
			������Ʈ.getData().add(���а迭);
			
			
			//���� ������ �迭[����] ����
			XYChart.Series ����迭 = new XYChart.Series();
			����迭.setName("����");
			//����Ʈ �� ����� ���������Ʈ ���
			ObservableList �����Ʈ = FXCollections.observableArrayList();
			for (int i = 0; i<list.size(); i++) {
				�����Ʈ.add(new XYChart.Data(list.get(i).getName(), list.get(i).getEng()));
			}
			����迭.setData(�����Ʈ);
			������Ʈ.getData().add(����迭);
			
			
			Button �ݱ��ư = (Button)parent.lookup("#btnclose");
			�ݱ��ư.setOnAction(e3 -> stage.close());
			
			Scene scene = new Scene(parent);
			stage.setTitle("�л��� ����׷���");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
	}//barChartAction
	
	//�߰� �޼ҵ�
	public void addAction(ActionEvent e) {
		
		try {
			Stage stage = new Stage(StageStyle.UTILITY);
			
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(btnadd.getScene().getWindow());
			
			Parent parent = FXMLLoader.load(getClass().getResource("form_p.fxml"));
			//parent ���� ��ư ȣ��
			Button btnformadd = (Button)parent.lookup("#btnformadd"); //1. ��ư ȣ���ؼ� ��üȭ
			//�����ư�� ������ ��
			btnformadd.setOnAction(e4 -> {//2. ��ư Ŭ������ ��
				TextField txtname = (TextField)parent.lookup("#txtname"); //3. ���� �ؽ�Ʈ�ʵ� ȣ�� ��üȭ
				TextField txtkor = (TextField)parent.lookup("#txtkor");
				TextField txtmath = (TextField)parent.lookup("#txtmath");
				TextField txteng = (TextField)parent.lookup("#txteng");
				
				list.add(new Student(txtname.getText(), Integer.parseInt(txtkor.getText()), Integer.parseInt(txtmath.getText()), 
						Integer.parseInt(txteng.getText())
						));
				stage.close(); //�������� �ݱ�
			});
			
			//��ҹ�ư�� ������ ��
			Button btnformcancel = (Button)parent.lookup("#btnformcancel");
			btnformcancel.setOnAction(e5 -> stage.close());
			
			Scene scene = new Scene(parent);
			stage.setTitle("�����Է�");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
	}//addAction
	
	public void pieChartAction(MouseEvent e) {
		
		if (e.getClickCount() != 2) {//Ŭ���� = 2[����Ŭ��]�� �ƴϸ� �޼ҵ� ����
			return;
		}
		
		try {
			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("piechart.fxml"));
			
			//��Ʈ�� ��üȭ
			PieChart ������Ʈ = (PieChart)parent.lookup("#piechart");
			//���̺� Ŭ���� �������� ��ü�� ���� -> Ŭ���� �л� ����
			Student temp = tableview.getSelectionModel().getSelectedItem();
			//������Ʈ�� ���߰�(����Ʈ)
			������Ʈ.setData(FXCollections.observableArrayList(
					new PieChart.Data("����", temp.getKor()),
					new PieChart.Data("����", temp.getMath()),
					new PieChart.Data("����", temp.getEng())
					));
			
			Button �ݱ��ư = (Button)parent.lookup("#btnclose");
			�ݱ��ư.setOnAction(e3 -> stage.close());
			
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("���κ� ������Ʈ");
			stage.setResizable(false);
			stage.show();
			
		}
		catch (Exception e2) {
			// TODO: handle exception
		}
		
		
		
		
	}//pieChartAction
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}//class
