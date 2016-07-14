/**
 * 
 */
package com.cmsz.eagleeye.util;

/**
 * @author Administrator
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SumLine {

	public static void main(String[] a) {
		String path = "C:/zc.txt";
		Scanner in;
		try {
			in = new Scanner(new File(path));
			List<Object> data = new ArrayList<Object>();
			while (in.hasNextLine()) {
				// 取第一行
				String s = in.nextLine();
				int idx = s.indexOf(" ");
				String s0 = s.substring(0, idx);
				String s1 = s.substring(idx + 1, s.length() - 1);
				String[] arrs = { s0, s1 };
				data.add(arrs);
			}
			for (int i = 0; i < data.size(); i++) {
				String[] arr = (String[]) data.get(i);
				System.out.println("第" + (i + 1) + "行是:" + arr[0] + "\t"
						+ arr[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}