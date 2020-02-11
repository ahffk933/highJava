package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
	/*
	 	InputStreamReadert객체는 파일의 인코딩 방식을 지정할 수 있다.
	 	형식) new InputStreamReader(바이트 기반 스트림 객체, 인코딩 방식)
	 	
	 	
	 	인코딩 방식에 대하여...
	 	
	 	한글 인코딩 방식은 크게 UTF-8 과 EUC-KR 방식으로 나누어 볼 수 있다.
	 	원래 한글 윈도우는 CP949를 사용했는데, 윈도우를 개발한 마이크로소프트에서 EUC-KR방식에서
	 	확장하였기 때문에 MS949라고도 부른다.
	 	한글 윈도우의 메모장에서 이야기하는 ANSI 인코딩이란 , CP949(Code Page 949)를 말함.
	 	CP949는 EUC-KR의 확장이며, 하위호환성이 있다.
	 	-	MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI계열)
	 	-	UTF-i    => 유니코드 UTF-8 인코딩 방식( 영문자 및 숫자 : 1bye, 한글 : 3bytes) : 가변적
	 	-	US-ASCII => 영문전용 인코딩 방식
	 	
	 	
	 	ANSI는 영어를 표기하기 위해 만든 코드로 규격자체에 한글이 없었다가 나중에 EUC-KR, CP949라는 식으로 한글이 포함되었음
	 	
	 	참고)
	 	 ASCII -> extended ASCII(ISO 8859-1)
	 	 	   -> 조합형(초성, 중성, 종성), 완성성(KSC 5601)
	 	 	   			=> 윈도우계열 : CP949(확장완성형)
	 	 	   			=> 유닛스계열 : EUC-KR(확장유닉스코드)
	 	 
	 	 *유니코드(Unicode)
	 	 서로 다른 문자 인코딩을 사용하는 컴퓨터간의 문서교환에 어려움을 겪게되고, 
	 	 이런 문제점을 해결하기 위해 전 세계의 모든 문자를 하나의 통일된 문자집합(Charset)으로 표현함
	 	 처음엔 2byte(65536)로 표현했지만, 부족해서 21bit(약 200만 문자)로 확장되었음
	 	  -> 보충문자(supplementary character)
	 
	 
	 */
	public static void main(String[] args) {
		//파일 인코딩을 이용하여 읽어오기
		//InputStreamReader => 바이트기반을 문자기반으로 변환해주는 스트임인 Reader로 변환시켜줌
		FileInputStream fin =null;
		InputStreamReader isr = null;
				
		
		try {
			
	
		//fin = new FileInputStream("e:/D_Other/test_utf8.txt");
		fin = new FileInputStream("e:/D_Other/test_ansi.txt");
		
		//isr = new InputStreamReader(fin);
		isr = new InputStreamReader(fin, "MS949");
		
		int c;
		while((c=isr.read()) != -1) {
			System.out.print((char)c);
		}
		System.out.println();
		System.out.println("출력 끝");
		
		isr.close();  //보조스트림만 닫아도 된다
		
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
