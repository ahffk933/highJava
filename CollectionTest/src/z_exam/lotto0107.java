
package z_exam;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class lotto0107 {
		
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int menu;
		
		do {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.println("메뉴선택 : ");
			
			menu = s.nextInt();
			
			switch (menu) {
			case 1:
				System.out.println("Lotto 구입 시작"); 
				System.out.println("금액입력");
				int money = s.nextInt();
				System.out.println("금액입력"+money);
				System.out.println("(1000원에 로또번호 하나입니다)");
							
				int lotto = money/1000;
				
				Set<Integer>intRnd	=	new HashSet<>();
				for(int i = 1;i<=lotto;i++) {
					while(intRnd.size()<6) {//	Set의 데이터가 6개 될때까지 반복함.
						int num	=	(int)(Math.random()*45+1);//1~45까지의 난수 만들기
						intRnd.add(num);
					}
					System.out.println("로또번호"+i+" : "+intRnd);
					intRnd.clear();
				}
				System.out.println();
				System.out.println("받은 금액은 "+money+"원이고 거스름돈은 "+money%1000+"원 입니다.");
				break;
			case 2: 
				System.out.println("이용해 주셔서 감사");
				menu = 0;
			break;
			}
			
		} while(menu != 0); 
	
	}
	
}


