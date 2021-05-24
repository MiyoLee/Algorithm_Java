package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_9229_한빈이와SpotMart_D3 {
	static int N, M;
	static int[] w;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 과자의 수
			M = Integer.parseInt(st.nextToken()); // 무게 합 제한
			w = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				w[i] = Integer.parseInt(st.nextToken());
			}

			answer = -1;
			dfs(0, 1, 0);
			System.out.println("#" + tc + " " + answer);
		}

	}

	public static void dfs(int cnt, int startidx, int weight) {
		if(weight > M) return;
		if (cnt == 2) {
			if (weight > answer) {
				answer = weight;
			}
			return;
		}
		for (int i = startidx; i <= N; i++) {
			dfs(cnt + 1, i+1, weight + w[i]);		//i를 선택
		}
	}
}
