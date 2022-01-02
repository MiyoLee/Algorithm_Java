package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10974_모든순열_S3 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //1<=N<=8
		Perm(0, new int[N], new boolean[N+1]);
	}
	public static void Perm(int cnt, int[] selected, boolean[] isSelected) {
		if(cnt==N) {
			for (int i = 0; i < selected.length; i++) {
				System.out.print(selected[i]);
				System.out.print(" ");
			}
			System.out.println();
			return;
		}
		for (int i = 1; i <= N; i++) {
			if(!isSelected[i]) {
				selected[cnt] = i;
				isSelected[i]=true;
				Perm(cnt+1, selected, isSelected);
				isSelected[i]=false;
			}
		}
	}
}
