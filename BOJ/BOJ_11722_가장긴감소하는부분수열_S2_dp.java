package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11722_가장긴감소하는부분수열_S2_dp {
	static int N;
	static int[] input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		//입력 완료
		//dp[i]: 0~i 중에 i를 선택하면서 가장 긴 원소의 길이
		int[] dp = new int[N];	//0~N-1	
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			dp[i]=1;
			for (int j = 0; j < i; j++) {
				if(input[j] > input[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					if(dp[i]>max)max=dp[i];
				}
			}
		}
		
		System.out.println(max);
	}
}
