package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class T04_ArrayListTest {
	//문제1) 5명의 별명을 입력하여 ArrayList에 저장하고 
	//		별명의 길이가 제일 긴 별명을 출력하시오
	//		(단, 각 별명의 길이는 모두 다르게 입력한다)
	
	//문제2) 문제1에서 별명의 길이가 같은 것을 여러개 입력했을 때에도 처리되도록 하시오
	
	public static void main(String[] args) {
		ArrayList<String> aliasList = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		System.out.println("별명입력");
		for(int i =0; i<= 5; i++) {
			String name = scan.next();
		}
		
		for(int i = 1; i <=5; i++) {
			System.out.println(i + "번째 별명 : ");
			String alias = scan.next();
			aliasList.add(alias);
		}
		
		int max = aliasList.get(0).length();
		for(int i=1; i<aliasList.size(); i++) {
			if(max < aliasList.get(i).length()) {
				max = aliasList.get(i).length();
			}
		}
		System.out.println("제일 긴 별명 :  ");
		for(int i =0; i<aliasList.size(); i++) {
			if(max == aliasList.get(i).length()) {
				System.out.println(aliasList.get(i));
			}
		}
	}

}
