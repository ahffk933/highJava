package kr.or.ddit.basic;

import java.io.BufferedOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Assignment {
	public static void main(String[] args) throws IOException{

			
			FileInputStream fis = new FileInputStream("e:/D_Other/Tulips.jpg");
			FileOutputStream fos = new FileOutputStream("e:/D_Other/copy_Tulips.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fos);
			
			int data;
			while((data = fis.read())!= -1) {
				bout.write(data);
			}
				
				bout.close();
				System.out.println("copy completed");
				
				fis.close();
				fos.close();
		
		}

	}
