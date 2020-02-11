package kr.or.ddit.basic;

public class Service {
	
	@PrintAnnotation()
	public void method1() {
		System.out.println("메서드1에서 출력되었다");
		
	}
	@PrintAnnotation("%")  //타입요소가 value일때만 value생략 가능 다른 타입일때는 다 써줘야함
	public void method2() {
		System.out.println("메서드2에서 출력되었다");
		
	}
	@PrintAnnotation(value="#", count=25)
	public void method3() {
		System.out.println("메서드3에서 출력되었다");
		
	}

}
