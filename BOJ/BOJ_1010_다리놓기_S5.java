package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1010_다리놓기_S5 {
	static int totalCnt;
	static int N,M;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T;
		T = Integer.parseInt(br.readLine());	//테케 입력
		
		for (int tc = 0; tc < T; tc++) {		//테케수만큼 반복
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//서쪽의 사이트 갯수
			M = Integer.parseInt(st.nextToken());	//동쪽의 사이트 갯수
			// 0<N<=M<30
			// M개 중 N개를 뽑는 경우의수 mCn
			totalCnt=0;
			dp = new int[M+1][N+1];
			
			System.out.println(Comb(M,N));
		}	
	}
	
	//a개중 b개를 뽑는 경우의 수를 구한다.
	private static int Comb(int a, int b) {
		if(dp[a][b] > 0) return dp[a][b];
		else if(a==b || b==0) return 1;
		return dp[a][b] = Comb(a-1,b-1) + Comb(a-1,b);
	}
	
}
