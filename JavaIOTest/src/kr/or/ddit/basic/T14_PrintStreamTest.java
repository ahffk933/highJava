package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/*
 	프린터기능 제공 보조 스트림 (출력해주는 역할의 보조 스트림들)
 */
public class T14_PrintStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fout = new FileOutputStream("e:/D_Other/print.txt");
		FileOutputStream fout2 = new FileOutputStream("e:/D_Other/print2.txt");
		
		PrintStream out = new PrintStream(System.out);
		out.print("Hello this is PrintStream \n");
		out.print("Hello this is PrintStream 2");
		out.print("Hello this is PrintStream 3");
		
		out.close();
		
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(fout2, "UTF-8"));
		writer.print("Hello this is PrintWriter \r\n");
		writer.print("Hello this is PrintWriter 2");
		writer.print("Hello this is PrintWriter 3");
		
		writer.close();
	}

}
