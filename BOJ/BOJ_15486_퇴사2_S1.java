package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486_퇴사2_S1 {
	static int N;
	static int[]t,p;
	static int max;
	static int dp[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		t = new int[N+1];	//기간 (1~N)
		p = new int[N+1];	//금액  (1~N)
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			t[i]=Integer.parseInt(st.nextToken());
			p[i]=Integer.parseInt(st.nextToken());
		}
		//입력 완료.
		//dp로 푼다.
		//1.dp배열을 뒤에서부터 채워나간다.
		//dp[i]: i일~N일까지 최대 이익
		dp = new int[N+2];
		dp[N+1] = 0;
		
		for (int i = N; i >= 1; i--) {
			if(i+t[i]>N+1) dp[i]=dp[i+1];
			else dp[i]=Math.max(p[i]+dp[i+t[i]], dp[i+1]);
		}
		
		System.out.println(dp[1]);
	}

}
