package kr.or.ddit.basic;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class T04_TcpServer {
	public static void main(String[] args) throws IOException {
		//TCP socket통신을 하기 위해 ServerSoket객체 생성
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버접속 기다리는 중");
		
		//accept()메서드는 Client에서 '연결 요청이 올 때까지 계속 기다림'
		//연결요청이 오면 Socket객체를 생성해 Client의 Socket과 연결한다
		Socket socket = server.accept();
		
		//---------------------------------------------------
		//이 이후로는 클라이언트와 연결된 후의 작업을 진행하면 된다
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소: " + socket.getInetAddress());
		
		//Client에게 메시지 보내기
		
		//OutputStream객체를 구성하여 전송
		//접속한 Socket의 getOutputStream()메서드를 이용하여 구한다
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("어서오거라");  //메세지 보내기
		System.out.println("메세지 전송완료");
		
		dos.close();
	}

}
