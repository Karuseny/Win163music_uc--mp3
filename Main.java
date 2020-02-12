
import java.io.*;
import java.util.Scanner;

public class Main {
	public static void translate(String file) {
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {
			String outString;
			outString = file.replace(".uc", ".mp3");
			File inFile = new File(file);
			File outFile = new File(outString);
			dis = new DataInputStream(new FileInputStream(inFile));
			dos = new DataOutputStream(new FileOutputStream(outFile));
			System.out.println("[#] File_in:"+inFile);
			System.out.println("[#] File_out:"+outFile);
			byte[] b = new byte[1024];
			int len;
			while ((len = dis.read(b)) != -1) {
				for (int i = 0; i < len; i++) {
					b[i] ^= 0xa3;
				}
				dos.write(b, 0, len);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {

		System.out.println("[1].dir [2].file");
		Scanner sc = new Scanner(System.in);
		try {
			int a = sc.nextInt();
			if(a==2) {
				String file=sc.next();
				translate(file);
			}else if (a == 1) {
				String filePath = sc.next();
				File file = new File(filePath);
				File[] tempList = file.listFiles();
				for (int i = 0; i < tempList.length; i++) {
					if (tempList[i].isFile()) {
						File tempFile = tempList[i];
						String fileName = tempFile.getName();
						if (fileName.indexOf(".uc") == fileName.length() - 3) {
								translate(filePath+"\\"+fileName);
						}
					}
				}

		} else {
			System.out.println("[!] ERROR please input 1 or 2");
		}
		
		}catch (Exception e) {
			System.out.println("[!] ERROR");
		}
		sc.close();
		
	}
}
