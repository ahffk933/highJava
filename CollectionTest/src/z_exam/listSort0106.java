package z_exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class listSort0106 {
	/*학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
	  Student클래스를 만든다.
	  생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
	  
	  이 Student객체들은 List에 저장하여 관리한다.
	  
	  List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
	  
	  총점의 역순으로 정렬하는 부분을 프로그램 하시오.
	  (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
	  
	  (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
	   총점 정렬기준은 외부클래스에서 제공하도록 한다.)*/
	public static void main(String[] args) {
		List<Student> studList = new ArrayList<>();
		
		studList.add(new Student("20191101", "ash", 70, 92, 94));
		studList.add(new Student("20191104", "jinx", 79, 83, 92));
		studList.add(new Student("20191103", "sona", 92, 79, 84));
		studList.add(new Student("20191102", "zed", 77, 84, 69));
		studList.add(new Student("20191105", "olaf", 86, 91, 88));
		
		System.out.println("정렬 전 ");
		for(Student stud : studList) {
			System.out.println(stud);
		}
		System.out.println("===============================================================");
		
		Collections.sort(studList);
		
		System.out.println("학번의 오름차순");
		for(Student stud : studList) {
			System.out.println(stud);
		}
		System.out.println("===============================================================");
		int total = 0;
		for(int i = 0; i <studList.size(); i++) {
			total = studList.get(i).getKorscore() +
					studList.get(i).getEngscore() +
					studList.get(i).getMathscore();
			studList.get(i).settotal(total);
		}System.out.println(" ");
		
		//석차
		ArrayList<Integer> rank = new ArrayList<Integer>(studList.size());
		for(int i = 0; i < studList.size(); i++) {
			rank.add(1);
			
		}
		for(int i = 0; i < studList.size(); i++) {
			for(int j = 0; j < studList.size(); j++) {
				if(studList.get(i).gettotal() < studList.get(j).gettotal()) {
					rank.set(i, rank.get(i)+1);
					
				}
			}
		}
		for(int i = 0; i < studList.size(); i++) {
			studList.get(i).setRank(rank.get(i));
		
		}
		
		Collections.sort(studList, new SortTotalDesc());
		System.out.println("총점의 내림차순");
		for(Student stud : studList) {
			System.out.println(stud);
		}
			
	}

}
class SortTotalDesc implements Comparator<Student>{

	@Override
	public int compare(Student stud1, Student stud2) {
		
		return new Integer(stud1.gettotal()).compareTo(stud2.gettotal()) * -1;
	}
	
}
class Student implements Comparable<Student>{
	private String id;
	private String name;
	private int korscore;
	private int engscore;
	private int mathscore;
	private int total;
	private int Rank;
	
	
	public Student(String id, String name, int korscore, int engscore, int mathscore) {
		super();
		this.id = id;
		this.name = name;
		this.korscore = korscore;
		this.engscore = engscore;
		this.mathscore = mathscore;
		this.total = total;
		this.Rank = Rank;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKorscore() {
		return korscore;
	}
	public void setKorscore(int korscore) {
		this.korscore = korscore;
	}
	public int getEngscore() {
		return engscore;
	}
	public void setEngscore(int engscore) {
		this.engscore = engscore;
	}
	public int getMathscore() {
		return mathscore;
	}
	public void setMathscore(int mathscore) {
		this.mathscore = mathscore;
	}
	public int gettotal() {
		return total;
	}
	
	public void settotal(int total) {
		this.total = total;
	}
	
	public int getRank() {
		return Rank;
	}
	
	public void setRank(int rank) {
		this.Rank = rank;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Student [id=" + id 
				+ ", name=" + name 
				+ ", korscore=" + korscore 
				+ ", engscore=" + engscore
				+ ", mathscore=" + mathscore 
				+ ", total= " + total
				+ ", rank= " + Rank
				+ ",]";
	}
	@Override
	public int compareTo(Student stud) {
		
		return getId().compareTo(stud.getId());
	}

}