package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 	vector, Hashtable등의 예전부터 존재하던 Collection들은 내부에 동기화 처리가 되어있다
 	but, 최근 새로 구성된 Collection class들은 동기화 처리가 되어있지 않아서, (hashmap, 최근 배운것들 거의,,)
 	 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리가 필요로 하다
 	 (동기화 처리는 안해주면 좋음 속도가 느려져서,, 필요한 경우 synchronized blahblah(8가지가있슴) 수동으로 해주면됨)
 * @author PC-13
 *
 */
public class T18_SyncCollectionTest {
	//동기화를 하지 않는 경우
	private static List<Integer> list1 = new ArrayList<>();
	
	//동기화하는 경우
	//Collections의 정적 메서드 중 synchronized로 시작하는 메서드 이름
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	

	public static void main(String[] args) {
		// 익명 클래스로 쓰레드 구현
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=10000; i++) {
				//	list1.add(i);  //동기화 처리를 하지 않은 리스트 사용
					list2.add(i);  //동기화 처리가 된 리스트 사용
				}
				
			}
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r) };
		
		for(Thread th : ths) {
			th.start();
		}
		for(Thread th: ths) {
			try {
				th.join();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	//	System.out.println("list1의 개수: " + list1.size());  //에러동반
		System.out.println("list2의 개수: " + list2.size());

	}

}
