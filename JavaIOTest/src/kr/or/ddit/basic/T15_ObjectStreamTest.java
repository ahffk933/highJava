package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T15_ObjectStreamTest {
	public static void main(String[] args) {
		// Member instance생성
		Member mem1 = new Member("hanna", 18, "van");
		Member mem2 = new Member("janna", 28, "van");
		Member mem3 = new Member("sona", 19, "van");
		Member mem4 = new Member("lux", 24, "van");
		
		try {
			//객체를 파일에 저장하기
			
			//출력용 스트림 객체 생성 ->중간에 fit에 있는 데이터들 모으는 과정 필요(직렬화 과정 필요)
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("e:/D_Other/memObj.bin")));
			
			//쓰기 작업
			oos.writeObject(mem1);  //직렬화
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			
			System.out.println("쓰기작업 완료");
			oos.close();
			
			//=======================================================
			
			//저장한 객체를 읽어와 출력하기
			
			//입력용 스트림 객체 생성 			//보조스트림      
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("e:/D_Other/memObj.bin")));
						//보조					//기본
			Object obj = null;
			
			try {
				while((obj = ois.readObject()) != null) {
					//읽어온 데이터를ㄹ 원래의 객체형으로 변환 후 사용한다
					Member mem = (Member)obj;
					System.out.println("이름: " + mem.getName());
					System.out.println("나이: " + mem.getAge());
					System.out.println("주소: " + mem.getAddr());
				}
				ois.close();
			}catch (ClassNotFoundException e) {
			}
		}catch (IOException e) {
			e.printStackTrace();
			//더이상 읽어올 객체가 없으면 예외 발생
			System.out.println("출력작업 끝");
		}
	}

}

class Member implements Serializable{   //객체를 <-serializable를 통해 직렬화하는것
//자바는 Serializable interface를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있슴
//구현안하면 직렬화 작업 시 java.io.NotSerializableException 예외발생
	
	//transient(:일시적인..(<-키워드임)) => 직렬화가 되지 않을 멤버변수에 지정
	//			   			(* static필드도 직렬화가 되지 않는다)
	//						직렬화가 되지 않는 멤버변수는 기본값으로 저장된다
	//						(참조형 변수 : null, 숫자형 변수 : 0)
	private String name;
	private transient int age;
	private String addr;
	
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}