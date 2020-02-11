package kr.or.ddit.basic;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
문제) 이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만들고, 이 Phone클래스를 이용하여 
	  전화번호 정보를 관리하는 프로그램을 완성하시오.
	  이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있다.
	  
	  전체의 전화번호 정보는 Map을 이용하여 관리한다.
	  (key는 '이름'으로 하고 value는 'Phone클래스의 인스턴스'로 한다.)
*/


public class PhoneBookTest {
	private Scanner scan;
	private Map<String, Phone> phoneBookMap;
	
	public PhoneBookTest() {  //2
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<>();
	}
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 6. 저장");
		System.out.println(" 7. 불러오기");
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 번호입력 >> ");		
	}
	
	// 프로그램을 시작하는 메서드
	public void phoneBookStart(){ 

		while(true){
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력  //변수 = 입력받아
			
			switch(menuNum){
			case 1:
				insert(); // 등록
				break;
			case 2:
				update(); // 수정
				break;
			case 3:
				delete(); // 삭제
				break;
			case 4:
				search(); // 검색
				break;
			case 5:
				displayAll(); // 전체 출력
				break;
			case 6:
				save(); // 전체 저장
				break;
			case 7:
				load(); // 불러오기
				break;

			case 0:
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문   //3번 끝
	}
	private void load() {
		Set<String> keySet = phoneBookMap.keySet();
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("e:/D_Other/phone/phone.txt")));
			
			Object obj = null;
			
			phoneBookMap.clear();
			
			try {
				while((obj = ois.readObject()) != null) {
					//읽어온 데이터를 원래 객체형으로 변환 후 사용
					Phone ph = (Phone)obj;
					System.out.println("이름 : " + ph.getName());
					System.out.println("telNum : " + ph.getTel());
					System.out.println("Adderess : " + ph.getAddr());
					
					phoneBookMap.put(ph.getName(), ph);
				}
				ois.close();
			}catch (ClassNotFoundException e) {
			}catch (EOFException e) {
			}
			
				
		}catch (IOException e) {
			e.printStackTrace();
			
			System.out.println("출력작업 끝");
		}
	}
	
	private void save() {
		Set<String> keySet = phoneBookMap.keySet();
		
		try {
			//객체를 파일에 저장
			//출력용 스트림 객체 생성
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("e:/D_Other/phone/phone.txt")));
			
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String name = it.next();  //키값을 얻는다
				Phone p = phoneBookMap.get(name);
				System.out.println(p.toString());
				//쓰기 작업
				oos.writeObject(p);  //직렬화
			}
			
			System.out.println("file has saved");
			oos.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//검색 method
	private void search() {
		System.out.println();
		System.out.println("검색할 번호의 정보를 입력하시오");
		System.out.println("이름 >> ");
		String name = scan.next();
		
		Phone p = phoneBookMap.get(name);
		if(p == null) {
			System.out.println(name + "님의 정보가 읎슴");
			
		}else {
			System.out.println(name + "님의 정보");
			System.out.println("이름 : " + p.getName());
			System.out.println("번호 : " + p.getTel());
			System.out.println("주소 : " + p.getAddr());
		}
		System.out.println("검색작업 완료");
	}
	//삭제 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 번호정보를 입력하시오");
		System.out.println("이름 >>");
		String name = scan.next();
		
		//remone(key) => 삭제 성공하면 삭제된 value값을 반환하고 실패하면 null을 반환한다
		if(phoneBookMap.remove(name) ==null) {
			System.out.println(name + "님은 등록되지 않은 정보임다");
		}else {
			System.out.println(name + " 님 정보를 삭제했슴다");
		}
		System.out.println("삭제작업 완료");
	}
	//수정 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 번호정보를 입력하시오");
		System.out.println("이름 >>");
		String name = scan.next();
		
		//수정할 자료가 있는지 검사
		if(phoneBookMap.get(name)== null) {
			System.out.println(name + "님의 정보가 읎슴");
			return;
		}
		System.out.println("전화번호 >>");
		String tel = scan.next();
		
		System.out.println("주소 >>");
		scan.nextLine();  //버퍼에 남아있을지도 모를 엔터키값 제거
		
		String addr = scan.nextLine();
		
		Phone p = new Phone(name, tel, addr);
		//같은 key값에 데이터를 저장하면 value값이 변경된다
		phoneBookMap.put(name, p);
		System.out.println(name + "님 정보 수정 완료");
		
		
		}
	//등록 메서드
	private void displayAll() {
		Set<String> keySet = phoneBookMap.keySet();
		System.out.println("=================================================");
		System.out.println("번호\t이름\t전화번호\t\t주소");
		System.out.println("=================================================");
		
		if(keySet.size()==0) {
			System.out.println("등록된 전화번호 정보가 존재하지 않슴다");
			
		}else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				String name = it.next();
				Phone p = phoneBookMap.get(name);
				
				System.out.println(" " + cnt + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
			}
		}
		System.out.println("=================================================");
		System.out.println("출력완료");
	}
	
	// 새로운 전화번호 정보를 등록하는 메서드
	//이미 등록된 사람은 등록되지 않는다
	private void insert() {   //4
		System.out.println();
		System.out.println("새롭게 등록한 전화번호 정보를 입력하라");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		//이미 등록된 사람인지 검사
		//get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 반환한다
		if(phoneBookMap.get(name) != null) {  //hashMap
			System.out.println(name + "님은 이미 등록된 사람임");
			return;  //exit method
		}
		System.out.print("전화번호 >>");
		String tel = scan.next();
		
		System.out.print("주소 >>");
		scan.nextLine(); 
		//입력버퍼에 남아있는 엔터키까지 읽어와버리는 역할을 수행한당
		//next()호출 후 nextLine()호출시 혹시 남아있을지 모를 쓰레기 값을 위해 한번 호출한다
		String addr = scan.nextLine();
		
		phoneBookMap.put(name, new Phone(name, tel, addr));
		System.out.println(name + "님 등록완료~");
		
		
	}

	public static void main(String[] args) {  //1
		new PhoneBookTest().phoneBookStart();
		
	}	

}
/*
 * 전화번호 정보를 저장할 수 있는 VO 클래스
 */
class Phone implements Serializable{    //멤버변수 선언 (이름, 번호, 주소)  //5
	
	private static final long serialVersionUID = 6125390201992101113L;
	
	
	private String name;
	private String tel;
	private String addr;
	
	
	public Phone(String name, String tel, String addr) {  //6 using field
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	@Override  //7  toString
	public String toString() {
		return "Phone [name=" + name + 
				", tel=" + tel + 
				", addr=" + addr + "]";
	}
	
	
	
	
}


