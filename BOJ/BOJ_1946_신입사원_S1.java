package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1946_신입사원_S1 {
	static int T;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			
			int ans = 0;
			N = Integer.parseInt(br.readLine());
			int[] rank = new int[N+1];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				rank[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
			}
			//입력완료
			
			int min= rank[1];
			++ans;
			
			for (int i = 2; i <= N; i++) {
				if(rank[i] < min) {
					++ans;
					min = rank[i];
				}
			}
			System.out.println(ans);
		}
				
	}
}
