package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 단일 쓰레드에서의 사용자 입력처리
 * @author PC-13
 *
 */
public class T05_ThreadTest {
	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("암거나 입력해");
		System.out.println("입력한 값은 : " + str + "임다");
		
		for(int i=10; i >=1; i--) {
			System.out.println(i);
			
			try {
				Thread.sleep(1000);  //1초동안 잠시 멈춤
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
