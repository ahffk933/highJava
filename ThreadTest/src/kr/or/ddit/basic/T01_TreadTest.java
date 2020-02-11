package kr.or.ddit.basic;

public class T01_TreadTest {
	public static void main(String[] args) {
		// single tread program
		for(int i=1; i<=200; i++) {
			System.out.print("*");
		}
		System.out.println();
		
		for(int i=1; i<=200; i++) {
			System.out.print("$");
		}
	}

}
