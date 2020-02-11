package z_exam;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

public class Assignment0103{

	public static void main(String[] args) {

		List list1 = new ArrayList();

		list1.add('a');
		list1.add('b');
		list1.add('c');
		list1.add('d');
		list1.add('e');
		list1.add('f');

		// void add(int index, Object element)
		// boolean addAll(int index, Collection c)
		// 지정된 위치(index)에 객체(element) 또는 컬렉션에 포함된 객체들을 추가한다
		List list2 = new ArrayList();

		list2.add(0, list1);
		list2.addAll(1, list1);
		System.out.println(list2.size());
		System.out.println(list2);

		// Object get(int index)
		// 지정된 index에 있는 객체를 반환한다
		System.out.println("get 3rd index : " + list1.get(3));

		// int indexOf(Object o)
		// 지정된 객체의 index를 반환 (List의 첫번째 요소부터 순방향으로 찾음)
		System.out.println(list1.indexOf('f')); // 0=a, 1=b, 2=c...

		// int lastIndexOf(Object o)
		// 지정된 객체의 index를 반환 (List의 마지막 요소부터 역방향으로 찾음)
		System.out.println(list1.lastIndexOf('c'));

		System.out.println("========================================");
		// ListIterator listIterator()
		// ListIterator listIterator(int index)
		// List의 객체에 접근할 수 있는 ListIterator를 반환한다
		
		ListIterator itr1 = list1.listIterator();
		System.out.println(itr1.hasNext());
		System.out.println(itr1.hasPrevious());
		
		
		int counter = 0;
		ListIterator itr = list1.listIterator();
		for (int i = 0; i < list1.size(); i++) {
			counter++;

			System.out.println("Element [" + counter + "] = " + itr.next());
		}System.out.println();
		

		itr = list1.listIterator();

		while(itr.hasNext()) {
		
			System.out.println("hasNext = " + itr.next()); // 순방향
		}
		
			while (itr.hasPrevious()) {
				
				System.out.println("hasPrevious = " + itr.previous()); // 역방향으로 읽음
			
	}
		itr = list1.listIterator();
		for(int i = 0; i < list1.size(); i++) {
			System.out.println("Iterator : " + itr.next());
		}
			
			
		System.out.println();

		System.out.println("nextIndex = " + itr.nextIndex());

		System.out.println("=======================================");
		// Object remove(int index)
		// 지정된 index에 있는 객체를 삭제하고 삭제된 객체를 반환한다
		list1.remove(0);
		System.out.println(list1);
		System.out.println("remove 'a' then size is : " + list1.size());

		// Object set(int index, Object elememt)
		// 지정된 index에 객체(element)를 저장한다
		list1.set(3, 'x');
		System.out.println("set 3rd index for 'x' = " + list1);
		System.out.println("was 'e' now is : " + list1.get(3));

		// void sort(Comparator c)
		// 지정된 비교자(comparator)로 객체(element)를 저장한다 (like a-b-c-d-e 순으로 정렬?)
		list1.sort(null);
		System.out.println("sort the list1 : " + list1);

		// List subList(int fromIndex, int toIndex)
		// 지정된 범위(fromIndex - toIndex)에 있는 객체를 반환한다 (-1)
		System.out.println(list1.subList(1, 3)); 
	
	}

}

