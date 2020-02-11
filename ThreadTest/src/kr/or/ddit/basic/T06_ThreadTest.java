package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * multi Thread에서의 사용자 입력처리
 * @author PC-13
 *
 */
public class T06_ThreadTest {
	//입력 여부를 확인하기 위한 변수선언
	//모든 클래스에서 공통으로 사용할 변수
	public static boolean inputCheck =false;
	
	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
	}

}
class DataInput extends Thread{
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("암거나 입력하슈");
				
			//입력완료되면 inputCheck변수를 true로 변경한다
		T06_ThreadTest.inputCheck = true;
		
		System.out.println("입력한 값은 " + str + "임");
	}
}
/**
 *  카운트다운을 처리하는 쓰레드
 */
class CountDown extends Thread{
	@Override
	public void run() {
		for(int i = 10; i>=1; i--) {
			//입력이 완료되었는지 여부를 검사하고 입력이 완료되면 run()메서드를 종료시킨다 즉, 현재메서드를 종료
			if(T06_ThreadTest.inputCheck) {
				return; //run()메서드가 종료되며ㅕㄴ 쓰레드도 끝남
			}
			System.out.println();
			
			try {
				Thread.sleep(10000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		//10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다
		System.out.println("over 10s exit programm");
		System.exit(0);  //프로그램 종료시키는 명령
	}
}
