package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class T07_GridPaneTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane grid = new GridPane();
		grid.setPrefSize(300, 200);
		grid.setPadding(new Insets(10));
		grid.setHgap(10);  //좌우 간격
		grid.setVgap(20);  //상하 간격
		
		Label label1 = new Label("ID");
		Label label2 = new Label("PW");
		
		TextField txtField1 = new TextField();
		//컨트롤의 글자색, 배경색, 배경이미지들은 CSS를 이용해 설정할 수 있다
		txtField1.setStyle("-fx-text-fill:darkblue; " + "-fx-background-color:lightblue;");
		
		//TextFeild txtField2 = new TextField();
		PasswordField passField = new PasswordField();
		passField.setStyle("-fx-text-fill:darkblue; " + "-fx-background-color:lightblue;");
		
		Button btn1 = new Button("log in");
		Button btn2 = new Button("Cancel");
		
		//GridPane에 컨트롤을 추가하는 방법
		//객체변수(grid).add(추가할 컨트롤, 칸번호, 행번호, colspan수, rowspan수);
		grid.add(label1, 1, 1);
		grid.add(label2, 1, 2);
		grid.add(txtField1, 3, 1, 2, 1);
		grid.add(passField, 3, 2, 2, 1);
		grid.add(btn1, 3, 4);
		grid.add(btn2, 4, 4);
		
		grid.setStyle("-fx-background-color:orange;border:1px;");
		
		Scene scene = new Scene(grid);
		primaryStage.setTitle("GridPane연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
