package kr.or.ddit.basic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T12_UdpSocketServer {
	private DatagramSocket socket;
	
	public void start() throws IOException{
		socket = new DatagramSocket(8888);
		DatagramPacket inPacket, outPacket; //패킷송수신을 위한 객체변수
		
		byte[] inMsg = new byte[1];  //패킷수신은 위한 바이트 배열 선언
		byte[] outMsg;				 //패킷송신을 위한 바이트 배열 선언
		
		while(true) {
			
		//데이터를 수신하기 위한 패킷을 생성
			inPacket = new DatagramPacket(inMsg, inMsg.length);
		
		System.out.println("패킷 수신 대기중");
		
		//패킷을 통해 데이터를 수신(receive)
		socket.receive(inPacket);
		
		System.out.println("패킷 수신완료");
		
		//수신한 패킷으로부터 client의 IP주소와 Port를 얻는다
		InetAddress address = inPacket.getAddress();
		int port = inPacket.getPort();
		
		//서버의 현재시간을 시분초 형태([hh:mm:ss])로 반환한다
		SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
		String time = sdf.format(new Date());  //서버의 현재시간
		outMsg = time.getBytes();  //시간문자열을 byte배열로 변환
		
		//패킷을 생성하여 client에게 받던말던 일단 전송(send)
		outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
		socket.send(outPacket);  //전송시작
		}
		
	}
	public static void main(String[] args) {
		try {
			new T12_UdpSocketServer().start();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}


