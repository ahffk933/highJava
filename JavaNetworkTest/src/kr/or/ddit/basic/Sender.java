package kr.or.ddit.basic;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
	Socket socket;
	DataOutputStream dos;
	String name;
	
	public Sender(Socket socket) {  //받아오는것만 하는 클래스
		this.socket = socket;
		name = "[" + socket.getInetAddress() + ":" + socket.getPort() + "]";
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(dos != null) {
			try {
				dos.writeUTF(name + " >>> " + scan.nextLine());  //한줄 한줄씩 읽는거
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		scan.close();
	}

}