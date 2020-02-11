package kr.or.ddit.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.inf.RemoteInterface;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.TestVO;

/*
 	RMI용 서비스를 제공하는 개체는 RMI용 인터페이스를 구현하고, 
 	UnicastRemeteObject객페를 상속하여 작성한다
 */
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface{
	
	//RMI용 인터페이스를 구현한 객페의 생성자도 RemoteException을 throws하도록 작성한다
	protected RemoteServer() throws RemoteException {  //remoteException을 throws하도록 생성해줘야한다(default 생성)
		super();
	}
	public static void main(String[] args) {
		try {
			//구현한 RMI용 객체를 클라이언트에서 사용할 수 있도록 RMI서버에 등록한다
			
			//1 RMI용 인터페이스를 구현한 객체 생성
			RemoteInterface inf = new RemoteServer();
			
			//2 구현한 객체를 클라이언트에서 찾을 수 있도록 Registry객체를 생성하여 등록한다
			// 포트번호를 지정하여 Registry객체 생성(기본포트값: 1099)
			Registry reg = LocateRegistry.createRegistry(8888);   //서버를 하나만들어 임의로 8888 registry는 interface
			
			//Registry서버에 제공하는 객체 등록
			//형식) Registry객체 변수.rebind("객체의Alias", 등록할 객체변수);
			reg.rebind("server", inf);  //bind되있으면 다시 안되있음 bind하라는 의미로 rebind사용 "서버"를 찾아 inf를 
			
			System.out.println("서버 준비완료");
			
		}catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	//RMI용 인터페이스에서 선언한 메서드들을 구현한다

	@Override
	public int doRemotePrint(String str) throws RemoteException {
		int length = str.length();
		System.out.println("클라이언트에서 보내온 메시지: " +str);
		System.out.println("출력 끝");
		return length;
	}

	@Override
	public void doPrintList(List<String> list) throws RemoteException {
		System.out.println("클라이언트에서 보낸 List값들");
		for(int i =0; i<list.size(); i++) {
			System.out.println((i+1)+ "번째: " + list.get(i));
		}
		System.out.println("List출력 끝");
	}

	@Override
	public void doPrintVo(TestVO vo) throws RemoteException {
		System.out.println("클라이언트에서 보내온 TestVO객체값 출력");
		System.out.println("testId : " + vo.getTestId());
		System.out.println("testNum : " + vo.getTestNum());
	}

	@Override
	public void setFiles(FileInfoVO[] fInfo) throws RemoteException {
		FileOutputStream fos = null;
		String dir = "e:/C_Lib/";  //파일이 저장될 폴더 지정
		System.out.println("파일저장 시작");
		
		for(int i = 0; i<fInfo.length; i++) {
			try {
				fos = new FileOutputStream(dir + fInfo[i].getFileName());
				//클라이언트에서 전달한 파일 데어티(byte[])를 서버측에 저장한다
				fos.write(fInfo[i].getFileData());
				fos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("파일저장 완료");
	}

}
