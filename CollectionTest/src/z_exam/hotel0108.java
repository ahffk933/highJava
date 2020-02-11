package z_exam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class hotel0108 {
	private Scanner scan;
	private Map<String, guest> hotelMap;

	public hotel0108() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<String, guest>();

	}

	public static void main(String[] args) {
		new hotel0108().hotelCheckin();
	}

	private void hotelService() {
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("**************************");
		System.out.println("번호를 입력하세요");

	}

	// 프로그램 시작
	public void hotelCheckin() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다");
		System.out.println("**************************");

		while (true) {

			hotelService();

			int num = scan.nextInt();

			switch (num) {
			case 1:
				checkin();
				break;
			case 2:
				checkout();
				break;
			case 3:
				room();
				break;

			case 4:
				System.out.println("이용해 주셔서 감사합니당");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문

		}
	}

	private void checkin() {
		System.out.println();
		System.out.println("원하는 룸을 선택하세요");
		String rmnm = scan.next();
		System.out.println("이름을 입력하세요");
		String guestname = scan.next();

		if (hotelMap.get(rmnm) != null) {
			System.out.println(rmnm + "호실은 이미 예약되었습니다");
			return;
		}

		hotelMap.put(rmnm, new guest(guestname, rmnm));  //이름만 게스트에 넣어줘도 상관없슴
		System.out.println(rmnm + "체크인 되었습니다");  	 //정보가 이름,룸넘버밖에 없어서..
	}

	private void checkout() {
		System.out.println("체크아웃 할 룸의 호수를 입력하시오");
		System.out.println("호실 입력");
		String rmnm = scan.next();
		if (hotelMap.remove(rmnm) == null) {
			System.out.println(rmnm + "등록되지 않은 정보입니다");
		} else {
			System.out.println(rmnm + "호실의 정보를 삭제했습니다");
		}
	}

	private void room() {
		Set<String> keySet = hotelMap.keySet();
		System.out.println("=================================================");
		System.out.println("name \t roomNumber");
		System.out.println("=================================================");

		if (keySet.size() == 0) {
			System.out.println("등록된 room정보가 존재하지 않슴다");

		} else {
			Iterator<String> it = keySet.iterator();

			while (it.hasNext()) {

				String rmnm = it.next();
				guest h = hotelMap.get(rmnm);

				System.out.println(h.getGuestname() + "\t" + h.getRmnm());
			}
		}
		System.out.println("=================================================");
		System.out.println("출력완료");
	}
}

class guest {

	private String guestname;
	private String rmnm;

	public guest(String guestname, String rmnm) {   //생성자 
		super();
		this.guestname = guestname;
		this.rmnm = rmnm;
	}

	public String getGuestname() {
		return guestname;
	}

	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}

	public String getRmnm() {
		return rmnm;
	}

	public void setRmnm(String rmnm) {
		this.rmnm = rmnm;
	}

	@Override
	public String toString() {
		return "guest [guestname=" + guestname + ", rmnm=" + rmnm + "]";
	}

}
