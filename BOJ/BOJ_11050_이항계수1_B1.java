package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11050_이항계수1_B1 {
	
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N,K;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1];	//dp[a][b] = aCb
		// nCk를 구하라.
		System.out.println(Comb(N,K));
	}

	//nCk
	private static int Comb(int n, int k) {
		if(dp[n][k] > 0) return dp[n][k];
		else if(n==k || k==0) return 1;
		return dp[n][k]=Comb(n-1,k-1)+Comb(n-1,k);
	}
}
