package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.stage.Stage;
/*
 	Stage => Window창
 	Scene => 무대에서는 하나의 장면이 배치된다
 	
 	- javaFX가 실행되는 순서
 	
 	main() method -> launch() method -> 해당객체의 생성자 메서드 -> init() method -> start() method -> 사용 후 종료
 	=> stop() method
 */

public class T01_JavaFxLifeCycle extends Application{
	public T01_JavaFxLifeCycle() {
		System.out.println(Thread.currentThread().getName() + " : 생성자 메서드 호출");
	}
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : init() 메서드 호출");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Thread.currentThread().getName() + " : start() 메서드 호출");
		
		primaryStage.show();  //창 보이기
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println(Thread.currentThread().getName() + " : stop() 메서드 호출");
	}
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " : main() 메서드 호출");
		launch(args);  
	}

}
