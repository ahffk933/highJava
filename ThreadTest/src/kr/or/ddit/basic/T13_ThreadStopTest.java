package kr.or.ddit.basic;
/*
 	Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다
 	이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 나중에 실행되는 프로그램에 영향을 줄 수 있다
 	그리하여 현재는 stop()메서드는 비추천(Deprecated)로 되어있슴
 */
public class T13_ThreadStopTest {
	public static void main(String[] args) {
		ThreadStopEx1 th = new ThreadStopEx1();
			th.start();
			
			try {
				Thread.sleep(1000);
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			//th.stop();
			th.setStop(true);
			
			//interrupt()메서드를 이용한 쓰레드 멈추기
			ThreadStropEx2 th2 = new ThreadStropEx2();
			th2.start();
			
			try {
				Thread.sleep(1000);
				
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			th2.interrupt();  //인터럽트 걸기
		}
	}

/*
 * 상태 플래그를 활요한 쓰레드 멈추기
 */
class ThreadStopEx1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 처리중");
		}
		System.out.println("자원 처리중");
		System.out.println("실행 종료");
		
	}
}
/*
  	interrupt()메서드를 이용해 쓰레드를 멈추게 하는 방법
 */
class ThreadStropEx2 extends Thread{
	@Override
	public void run() {
		//방법1 => sleep()메서드나 join()메서드 등을 사용했을 때 interrupt()메서드를 호출하면
		//		  interrupt()메서드를 호출하면 InterruptedException이 발생한다
		try {
			while(true) {
				System.out.println("쓰레드 처리 중");
				Thread.sleep(1);  //없으면 인터럽트 안걸림 sleep했을때 interrupt가 방해해서 걸리는거
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
			
			//방법2 => interrupt()메서드가 호출되었는지 검사하기
			while(true) {
				System.out.println("쓰레드 처리 중");
				
				//검사방법1 -> 쓰레드의 인스턴스용 메서드를 이용하는 방법
				if(this.isInterrupted()) {  //interrupt메서드가 호출되면 true
					System.out.println("인스턴스용 isInterrupted()");
					break;
				}
				//검사방법2 -> 쓰레드의 정적 메서드 이용하는 방법
				if(Thread.interrupted()) {  //interrupt()메서드 호출되면 true
					System.out.println("정적 메서드 interrupted()");
					break;
			}
		}
		System.out.println("자원처리중");
		System.out.println("실행종료");
	}
}
