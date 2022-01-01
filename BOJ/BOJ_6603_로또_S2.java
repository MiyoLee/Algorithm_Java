package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6603_로또_S2 {
	static int K;
	static int[] S;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			if(K==0) break;
			
			S = new int[K];
			for (int i = 0; i < K; i++) {
				S[i]=Integer.parseInt(st.nextToken());
			}
			// 입력 완료
			Comb(0,0,new int[6]);
			System.out.println();
		}
	}
	
	static void Comb(int cnt, int startIdx, int[]selected) {
		if(cnt==6) {
			print(selected);
			return;
		}
		for (int i = startIdx; i < K; i++) {
			selected[cnt] = S[i];
			Comb(cnt+1, i+1, selected);
		}
	}
	
	static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			System.out.print(' ');
		}
		System.out.println();
	}
}
