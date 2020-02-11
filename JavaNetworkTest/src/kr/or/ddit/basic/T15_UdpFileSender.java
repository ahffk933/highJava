package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
  
public class T15_UdpFileSender {
	
    public static final int DEFAULT_BUFFER_SIZE = 10000;
    
    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int port = 8888;
         
        File file = new File("e:/D_Other/Tulips.jpg");
        DatagramSocket ds = null;
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다.");
            System.exit(0);
        }
        long fileSize = file.length();  //파일 사이즈 long type으로
        long totalReadBytes = 0;        //전체 몇 바이트냐 보여줄라고
 
        double startTime = 0;  
        try {
            ds = new DatagramSocket();
            InetAddress serverAdd = InetAddress.getByName(serverIP);
            startTime = System.currentTimeMillis();
            String str = "start"; // 전송 시작을 알려주기 위한 문자열
            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);  //문자열을 바이트단위로
            ds.send(dp);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
             
            str = String.valueOf(fileSize); // 총 파일 사이즈 정보를 알려줌.
            dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);
            ds.send(dp);  //스타트 문자열보냄
             
            while (true) {
                Thread.sleep(10); // 패킷전송간의 간격을 주기 위해서..
                int readBytes = fis.read(buffer, 0, buffer.length);  //->default buffer size=10000bytes
                if (readBytes == -1)   // ->더이상 읽을게 없다
                    break;
                dp = new DatagramPacket(buffer, readBytes, serverAdd, port); // 읽어온 파일내용 패킷에 담기
                ds.send(dp);
                totalReadBytes += readBytes;  //읽은 바이트 수 누적
                System.out.println("In progress: " + totalReadBytes + "/"
                        + fileSize + " Byte(s) ("
                        + (totalReadBytes * 100 / fileSize) + " %)");
            
            }//while문 끝
            double endTime = System.currentTimeMillis();
            double diffTime = (endTime - startTime)/ 1000;;
            double transferSpeed = (fileSize / 1000)/ diffTime;
             
            System.out.println("time: " + diffTime+ " second(s)");
            System.out.println("Average transfer speed: " + transferSpeed + " KB/s");
             
            str = "end"; // 전송이 완료되었음을 알리기 위한 문자열
            dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);
            ds.send(dp);
            //Thread.sleep(5000); 
            System.out.println("전송 완료...");
            fis.close();
            ds.close();
  
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
  
    }
  
}

