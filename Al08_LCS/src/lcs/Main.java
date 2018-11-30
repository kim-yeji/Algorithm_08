package lcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public void lcs_length(String X, String Y) throws IOException {

		
		int m = X.length();
		int n = Y.length();

		// 문자들을 담을 char배열 선언
		char[] x = new char[m];
		char[] y = new char[n];

		// 문자열로 받은 X,Y를 배열에 저장
		for (int i = 0; i < x.length; i++) {
			x[i] = (X.charAt(i));
		}
		for (int i = 0; i < y.length; i++) {
			y[i] = (Y.charAt(i));
		}

		
		LCS[][] c = new LCS[m + 1][n + 1]; // 값과 방향을 가진 객체들 저장배열

		// 첫번째는 어떠한 문자열과도 못만나기 때문에
		// 0으로 초기화
		for (int i = 1; i <= m; i++) {
			c[i][0] = new LCS(0,0);
		}
		for (int j = 0; j <= n; j++) {
			c[0][j] = new LCS(0,0);
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[i - 1] == y[j - 1]) {
					// 대각선에서 1 더한값 가져오기
					c[i][j] = new LCS(c[i - 1][j - 1].data + 1,1); // 1 = ↖
				} else if (c[i - 1][j].data >= c[i][j - 1].data) {
					c[i][j] = new LCS(c[i - 1][j].data,2); // 2 = ↑
				} else {
					c[i][j] = new LCS(c[i][j - 1].data,3); // 3 = ←
				}
			}
		}

		System.out.print("Matrix\n");
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print(c[i][j].data + " ");
			}
			System.out.println();
		}
		System.out.println("방향 Matrix");
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.print(c[i][j].direc + " ");
			}
			System.out.println();
		}
		
		print_LCS(c, x, m, n);
	}
 
	
	// x : 문자 행렬
	// i,j 행렬의 크기값을 가지고 있는 변수
	public void print_LCS(LCS c[][], char x[], int i, int j) throws IOException {
		File file = new File("C:/Users/Administrator/Desktop/result.txt");
		if (file.exists()) {
			file.delete();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		PrintWriter pw = new PrintWriter(bw, true);
		if (i == 0 || j == 0)
			return;
		if (c[i][j].direc == 1) {
			print_LCS(c, x, i - 1, j - 1);
			pw.print(x[i - 1]);
			System.out.print(x[i - 1]);
		} else if (c[i][j].direc == 2) {
			print_LCS(c, x, i - 1, j);
		} else
			print_LCS(c, x, i, j - 1);
		
		bw.close();
		pw.close();
	}

	public static void main(String[] args) throws IOException {

		Main main = new Main();
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/LCS_Data.txt"));
		List<String> list = new ArrayList<String>();

		int i = 0;
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			} else {
				list.add(line);
			}
			i++;
		}

		main.lcs_length(list.get(1), list.get(3));

		br.close();

	}

}
