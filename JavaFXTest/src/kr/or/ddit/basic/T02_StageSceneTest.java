package kr.or.ddit.basic;


import java.beans.EventHandler;
import java.nio.charset.MalformedInputException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class T02_StageSceneTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();  //컨트롤들을 세로로 배치해주는 컨테이너
		root.setPrefWidth(650);  //VBox의 너비
		root.setPrefHeight(150); //VBox의 높이
		root.setAlignment(Pos.CENTER);  //컨트롤들을 가운데 정렬
		root.setSpacing(20);  //컨트롤과 컨트롤 사이의 간격
		
		Label label = new Label();  //Label객체 생성 
		label.setText("안녕하셔 JavaFX여");
		label.setFont(new Font(50));  //font객체를 이용해 글자크기 설정
		
		Button btn = new Button();
		btn.setText("확인");
		btn.setOnAction(new javafx.event.EventHandler<ActionEvent>() {   
			//eventhandler없으면 버튼 눌러도 실행안됨
			
			@Override
			public void handle(ActionEvent event) {  //함수적 인터페이스
				// 처리할 내용을 기술하는 영역
				Platform.exit();  //프로그램 종료
				
			}
		});
		btn.setOnAction (e-> Platform.exit());  //람다식
		
		//VBox에 컨트롤들 추가   //ObservableList<Node>
		root.getChildren().add(label);
		root.getChildren().add(btn);
		
		//VBox를 rootcontainer로 하는 Scene객체 생성
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Stage와 Scene연습");  //창 제목
		primaryStage.setScene(scene); //stage에 scene설정
		primaryStage.show();  //창 보이기
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
