package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9663_NQueen_G5 {
	static int ans = 0;
	static int n;
	static int[] col;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		col = new int[n];	//각 행별로 퀸이 놓인 열의 위치
		Arrays.fill(col, -1);
		
		Sol(0);
		System.out.println(ans);
	}
	static void Sol(int row) {
		if(row>=n) {
			++ans;
			return;
		}
		for (int i = 0; i < n; i++) {	//col 순회
			col[row] = i;
			if(isAvailable(row,i)) {
				Sol(row+1);
			}
		}
	}
	
	private static boolean isAvailable(int r, int c) {
		for (int i = 0; i < r; i++) {
			if(col[i]>=0 && (Math.abs(c-col[i]) == Math.abs(r-i) || r==i || c==col[i])) {
				return false;
			}
		}
		return true;
	}
}
