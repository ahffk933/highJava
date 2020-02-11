package kr.or.ddit.basic;

public class T02_LambdaTest {

	public static void main(String[] args) {
		// 람다식을 사용하지 않았을 경우
/*		LambdaTestInterface1 lam1 = new LambdaTestInterface1() {
			
			@Override
			public void test() {
				System.out.println("Hi");
				System.out.println("익명 구현 객체 방식임");
				
			}
		};
		*/
		LambdaTestInterface1 lam1 = ()->{
			System.out.println("방갑1 \n 람다식 처리 방식임다");
		};
		lam1.test();  //메서드 호출
		
		//=======================================================================================

		/*
		 	람다식 작성 방법
		 	
		 	기본형식) (자료형 이름 매개변수명, ...) -> {실행문들; ...}
		 	
		 	 1) 매개변수의 '자료형이름'은 생략가능
		 	    예) (int a) -> { System.out.println(a);}
		 	 	 (a) -> { System.out.prinln(a);}
		 	 	
		 	 2) 매개변수가 1개일 경우 괄호'()' 생략가능
		 	  (단, '자료형이름'을 지정할 경우 괄호 생략 불가)
		 	    예) a -> { System.out.prinln(a);}
		 	  
		 	 3) '실행문'이 1개일 경우 '{}' 생략 가능
		 	  (이때 문장의 끝을 나타내는 세미콜론(;)도 생략한다)
		 	    예) a -> System.out.prinln(a)
		 	    
		 	 4) 매개변수가 하나도 없으면 괄호'()' 생략가능
		 	   예) ()-> System.out.prinln("안녕")
		 	   
		 	 5) 반환값이 있을 경우, return명령을 사용
		 	   예) (a,b) -> { return a+b;}
		 	 
		 	 6) 실행문에 return문만 있을 경우 return명령과 '{}'의 생략가능
		 	   예) (a,b) -> a+b
		 	   
		 */
		
		LambdaTestInterface2 lam3 = (int z) -> {
			int result = z + 100;
			System.out.println("result = " + result);
		};
		lam3.test(30);

		LambdaTestInterface2 lam4 = z -> {
			int result = z + 300;
			System.out.println("result = " + result);
		};
		lam4.test(60);

		LambdaTestInterface2 lam5 = z -> System.out.println("result = " + (z + 500));
		lam5.test(90);
		
		System.out.println("------------------------------------------------------");

		LambdaTestInterface3 lam6 = (int x, int y) -> {
			int r = x + y;
			return r;

		};
		int k = lam6.test(20, 50);
		System.out.println("k = " + k);

		LambdaTestInterface3 lam7 = (x, y) -> {
			return x + y;
		};
		k = lam7.test(80, 50);
		System.out.println("k = " + k);

		LambdaTestInterface3 lam8 = (x, y) -> x + y;
		k = lam8.test(100, 200);
		System.out.println("k= " + k);

		LambdaTestInterface3 lam9 = (x, y) -> {
			return x > y ? x : y;
		};
		k = lam9.test(100, 200);
		System.out.println("k = " + k);
		 
	}

}
