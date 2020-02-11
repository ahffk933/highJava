package kr.or.ddit.basic;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class T14_TableViewTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		//TableView에 나타낼 데이터 구성
		ObservableList<Member> data = FXCollections.observableArrayList
				(new Member("구한나", "shelley", 28, "7788987702", "coquitlam"),
				new Member("구명석", "david", 62, "7788556060", "van"),
				new Member("최미옥", "amy", 55, "6045987702", "surrey"),
				new Member("구세일", "sail", 25, "2362557702", "wvan")
				
				);
		
		BorderPane root = new BorderPane();
		
		//TableView에 데이터 세팅하기
		TableView<Member> table = new TableView<>(data);
		
		TableColumn<Member, String> nameCol = new TableColumn<>("name");
		TableColumn<Member, String> korNameCol = new TableColumn<>("한글이름");
		korNameCol.setStyle("-fx-alignment: CENTER;");  //중앙정렬
		
		//해당컬럼에 나타낼 데이터 연결
		//(출력할 객체의 멤버변수와 출력할 컬럼을 매칭시킨다)
		korNameCol.setCellValueFactory(new PropertyValueFactory<>("korName"));
		
		TableColumn<Member, String> engNameCol = new TableColumn<>("영어이름");
		engNameCol.setCellValueFactory(new PropertyValueFactory<>("engName"));
		
		nameCol.getColumns().addAll(korNameCol, engNameCol);
		
		TableColumn<Member, Integer> ageCol = new TableColumn<>("나이");
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		
		TableColumn<Member, String> telCol = new TableColumn<>("전화번호");
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		
		TableColumn<Member, String> addrCol = new TableColumn<>("주소");
		addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
		//생성된 각 컬럼들을 TableView에 추가
		table.getColumns().addAll(nameCol, ageCol, telCol, addrCol);
		
		//table.setItem(data);
		
		
		//==================================================
		GridPane grid = new GridPane();
		Text txt1 = new Text("한글이름");
		Text txt2 = new Text("영어이름");
		Text txt3 = new Text("나이");
		Text txt4 = new Text("전화번호");
		Text txt5 = new Text("주소");
		
		TextField txtKorName = new TextField();
		TextField txtEngName = new TextField();
		TextField txtAge = new TextField();
		TextField txtTel = new TextField();
		TextField txtAddr = new TextField();
		
		grid.add(txt1, 1, 1);
		grid.add(txt2, 2, 1);
		grid.add(txt3, 3, 1);
		grid.add(txt4, 4, 1);
		grid.add(txt5, 5, 1);
		
		grid.add(txtKorName, 1, 2);
		grid.add(txtEngName, 2, 2);
		grid.add(txtAge, 3, 2);
		grid.add(txtTel, 4, 2);
		grid.add(txtAddr, 5, 2);
		
		grid.setVgap(5);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 0, 10, 0));
		//------------------------------------------------------------------
		
		
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		
		Button btnAdd = new Button("추가");
		btnAdd.setOnAction(e->{
			if(txtKorName.getText().isEmpty() || txtEngName.getText().isEmpty() || txtAge.getText().isEmpty() || txtTel.getText().isEmpty()|| txtAddr.getText().isEmpty()) {
				
				System.out.println("빈 항목이 있다");
				
				return;
			}
			if(!Pattern.matches("^[0-9]+$", txtAge.getText())) {
				errMsg("데이터 오류", "나이는 정수형으로 입력하라");
				txtAge.requestFocus();   //해당 객체에 포커스 추가
				return;
			}
			data.add(new Member(txtKorName.getText(), txtEngName.getText(), Integer.parseInt(txtAge.getText()), txtTel.getText(), txtAddr.getText()));
					

			infoMsg("작업결과", txtKorName.getText()+ "의 정보를 추가하였다");
					
					txtKorName.clear();
					txtEngName.clear();
					txtAge.clear();
					txtTel.clear();
					txtAddr.clear();		
		
		
		});
		
		//TableView를 클릭했을때 처리
		table.setOnMouseClicked(e->{
			//TableView에서 선택한 줄의 데이터를 가져온다
			Member mem = table.getSelectionModel().getSelectedItem();
			
			txtKorName.setText(mem.getKorName());
			txtEngName.setText(mem.getEngName());
			txtAge.setText(String.valueOf(mem.getAge()));
			txtTel.setText(mem.getTel());
			txtAddr.setText(mem.getAddr());
		});
		
		//-------------------------------------------------------
		
		
		Button btnEdit = new Button("수정");
		btnEdit.setOnAction(e->{
			if(txtKorName.getText().isEmpty() 
					|| txtEngName.getText().isEmpty() 
					|| txtAge.getText().isEmpty() 
					|| txtTel.getText().isEmpty()
					|| txtAddr.getText().isEmpty()) {
				
				System.out.println("빈 항목이 있다");
				
				return;
			}
			if(!Pattern.matches("^[0-9]+$", txtAge.getText())) {
				System.out.println("데이터오류");
				txtAge.requestFocus();   //해당 객체에 포커스 추가
				return;
			}
			data.set(table.getSelectionModel().getSelectedIndex(),
					new Member(txtKorName.getText(), 
							txtEngName.getText(), 
							Integer.parseInt(txtAge.getText()), 
							txtTel.getText(), 
							txtAddr.getText()));
					

					infoMsg("작업결과", txtKorName.getText()+ "의 정보를 수정하였다");
					
					txtKorName.clear();
					txtEngName.clear();
					txtAge.clear();
					txtTel.clear();
					txtAddr.clear();		
		
		
		});
		
		Button btnDel = new Button("삭제");
		btnDel.setOnAction(e->{
			if(table.getSelectionModel().isEmpty()) {
				errMsg("작업오류", "삭제할 자료를 선택한 후 삭제하라");
				return;
			}
			
			data.remove(table.getSelectionModel().getSelectedIndex());
			
			infoMsg("작업결과", txtKorName.getText()+ "의 정보를 삭제하였다");
			
			txtKorName.clear();
			txtEngName.clear();
			txtAge.clear();
			txtTel.clear();
			txtAddr.clear();		



		});
		
		//------------------------------------------------------------------
		
		Button btnTest1 = new Button("속성연습1");
		btnTest1.setOnAction(e->{
			//TextField, TestArea등 문자를 입력하는 객체를 ReadOnly를 설정하는 메서드 => setEditable()
			//이 메서드에 True를 설정하면 '입력가능
			//false를 설정하면 '읽기전용'이 된다
			txtKorName.setEditable(false);
			txtEngName.setEditable(false);
			
			//객체를 비활성화 또는 활성화 시키는 메서드 => setDisabled()
			//이 메서드에 true를 설정하면 '비활성화'
			//false를 설정하면 '활성화'된다
			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			
			//입력상자에 흐릿하게 나타내는 메세지
			txtKorName.setPromptText("한글이름 입력");
			txtAddr.requestFocus();  //포커스 추가
			
		});
		
		Button btnTest2 = new Button("속성연습2");
		btnTest2.setOnAction(e->{
			txtKorName.setEditable(true);
			txtEngName.setEditable(true);
			
			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
		});
	
		
			
			vbox.getChildren().addAll(btnAdd, btnEdit , btnDel, btnTest1, btnTest2);
			
			root.setCenter(table);
			root.setRight(vbox);
			root.setBottom(grid);
			root.setPadding(new Insets(10));
			
			
			Scene scene = new Scene(root, 600, 400);
			
			primaryStage.setTitle("TableView 연습");
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("Error");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("information");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}


	
	
	public class Member {
		private String korName;
		private String engName;
		private int age;
		private String tel;
		private String addr;
		
	
		
	public Member(String korName, String engName, int age, String tel, String addr) {
		super();
		this.korName = korName;
		this.engName = engName;
		this.age = age;
		this.tel = tel;
		this.addr = addr;
	}
	
	
	public String getKorName() {
		return korName;
	}
	public void setKorName(String korName) {
		this.korName = korName;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	


}
}