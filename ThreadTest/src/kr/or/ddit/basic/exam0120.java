package kr.or.ddit.basic;
/*
 10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현) (comepareTo?)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 나타내시오.
예) random
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수 순으로 출력한다.

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class exam0120 {
	static String strRank ="";

	public static void main(String[] args) {
		
		List<Horse> hl = new ArrayList<Horse>();    //2
			List<Horse> list = new ArrayList<Horse>();
			list.add(new Horse("1.hecarim"));
			list.add(new Horse("2.sion"));
			list.add(new Horse("3.ramous"));
			list.add(new Horse("4.kayn"));
			list.add(new Horse("5.kaisa"));
			list.add(new Horse("6.talon"));
			list.add(new Horse("7.leblanc"));
			list.add(new Horse("8.kled"));
			list.add(new Horse("9.darious"));
			list.add(new Horse("0.leesin"));
			
			for(Horse horse : list) {   //3
				horse.start();
			}
			PrintRacing pr = new PrintRacing(list);   //4-1
			pr.start();
		}

	}
class PrintRacing extends Thread{   //4
	List<Horse> list;
	public PrintRacing(List<Horse> hr) {
		this.list = hr;
	}
	@Override
	public void run() {
		String[] arr = new String[50];  //배열활용
		int rank = 1;
		boolean ing = true;
		
		while(ing) {    //말이 달리고 도착하는거
			for(Horse hr2 : list) {
				if(hr2.isGoal()==true) {
					System.out.print(hr2.getHName()+ ":");
					for(int j=0; j<50; j++) {
						arr[j] = "□";
						System.out.print(arr[j]);
					}
					System.out.println();
					continue;
				}
				//위치 보여주기
				System.out.print(hr2.getHName()+ ":");
				for(int i=0; i<50; i++) {
					arr[i]= "-";
					if(hr2.getlocation() == i) {
						arr[i] = ">";
					}
			}
			for(int j =0; j <50; j++) {
				System.out.print(arr[j]);
			}
			System.out.println();
			
			if(hr2.getlocation() >=50) {   //도착하면 랭크에 담아줌
				hr2.setRank(rank);
				rank++;
				hr2.setGoal(true);
			}
		}
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            
            if (rank==11) {   //10말이니까 11되면 end
                
                System.out.println("★★★★★★★경기 끝!★★★★★★★");
                ing=false;
            }
        }
        Collections.sort(list);   //리스트 오름차순(기본) 정리
        for (Horse h2 : list) {
            System.out.printf(h2.getRank() + "등 : " + "\t" + h2.getHName());
            System.out.println();
        }
        System.exit(0);
    }

}	



class Horse extends Thread implements Comparable<Horse>{  //1
	private String name;  //말 이름
	private int rank = 0;  //랭크
	private int location = 0;   //현재위치
	public boolean goal = false; // 결승 지점 통과여부

	public Horse(String name) {
		super();
		this.name = name;
	}
	
	public int getlocation() {
		
		return location;
	}

	@Override
	public void run() {   //말달리는 구간 누군 빠르게 누군 느리게 랜덤으로 주기
		int cnt =0;
		while(true) {
			location +=cnt;
			try {
				Thread.sleep(300*(int)(Math.random()*4));
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		
		if(location == 50) {
			break;
		}
		cnt++;
		
	}
}

	@Override
	public int compareTo(Horse hr) {    //랭킹비교
		
		return Integer.compare(this.rank, hr.rank);
	}
	public String getHName() {
		return name;
	}
	public boolean isGoal() {
		return goal;
	}
	public void setGoal(boolean goal) {
		this.goal = goal;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}	