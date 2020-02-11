package kr.or.ddit.basic;

/*
  제너릭메서드<T,R> R method(T t)
  
  파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메서드
  
  선언방법 : 리턴타입 앞<> 기호넣고, 타입 파라미터를 기술 수 사용
 */
class Util{
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {  //1
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());  //5
		
		return keyCompare && valueCompare;  //둘다 true = ture 둘 중 하나라도 false면 = false / f, f = false
	}
}

/**
 * 
 * 멀티타입<K, V>을 가지는 제너릭 클래스
 *
 * @param <K>
 * @param <V>
 */
class Pair<K, V>{  //2
	private K key;
	private V value;
	
	public Pair(K key, V value) {  //3
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {  //4
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	
		
	}

public class T04_GenericMethodTest {
	public static void main(String[] args) {  //6
		
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "박보검");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "박보검");
		
		boolean result1 = Util.<Integer, String> compare(p1, p2);
		
		if(result1) {
			System.out.println("(p1, p2)논리(의미)적으로 동일한 객체");
		}else {
			System.out.println("논리(의미)적으로 동일하지 않음");
		}
		
		Pair<String, String> p3 = new Pair<>("001", "박보검");
		Pair<String, String> p4 = new Pair<>("002", "박보검");
		
		boolean result2 = Util.compare(p3, p4);
		
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체");
		}else {
			System.out.println("(p3, p4)논리(의미)적으로 동일하지 않음");
		}
		
	}

}
