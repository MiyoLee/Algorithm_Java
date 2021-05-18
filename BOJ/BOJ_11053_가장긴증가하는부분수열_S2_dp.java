package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11053_가장긴증가하는부분수열_S2_dp {
	static int N;
	static int[] arr;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//수열 입력 완료 
		dp = new int[N];	//dp[i]: 0~i까지 수 중 i를 포함하면서 가장 긴 부분 수열의 길이
		int max=0;
		for (int i = 0; i < N; i++) {
			dp[i]=1;
			for (int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
