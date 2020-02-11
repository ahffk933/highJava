package kr.or.ddit.basic;


import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.NameList;

public class T03_ArrayListTest {
	public static void main(String[] args) {
		//문제) 5명의 사람 이름을 입력하여 ArrayList에 저장하고 이 중 '김'씨의 이름을 출력하라
		//		(단, 입력은 Scanner를 이용하여 입력 받는다)
		
		ArrayList<String> nameList = new ArrayList();
		System.out.println("이름 입력");
		for(int i = 0; i<=5; i++) {
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		nameList.add(input);
		}
		
		System.out.println();
			
		System.out.println("'김'으로 시작하는 이름");
		for(int i=0; i<nameList.size(); i++) {
			String name = nameList.get(i);
			
			/*
			 * if(name.charAt(0)=='김'){
			 * 	System.out.println(name);
			 * 
			 * if(name.substring(0,1).equals("김")){
			 * 	System.out.println(name);
			 * 
			 * if(name.indexOf("김")==0){
			 *  System.out.println(name);
			 *  }
			 */
			if(name.startsWith("김")) {
				System.out.println(name);
			}
		}
		System.out.println("end");
	}

}
