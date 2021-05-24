package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거다이어트_D3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//재료 갯수
			int L = Integer.parseInt(st.nextToken());	//제한 칼로리
			int[][] dp = new int[N+1][L+1];		//재료 1~N, 제한칼로리 0~L
			int[] v = new int[N+1];		//맛 점수
			int[] k = new int[N+1]; 	//칼로리
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				v[i] = Integer.parseInt(st.nextToken());
				k[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i < dp.length; i++) {
				for (int j = 0; j < dp[i].length; j++) {
					if(j < k[i]) dp[i][j] = dp[i-1][j];
					else dp[i][j] = Math.max(dp[i-1][j], v[i]+dp[i-1][j-k[i]]);
				}
			}
			System.out.println("#"+tc+" "+dp[N][L]);
		}
	}
}
