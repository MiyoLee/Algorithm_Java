package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1217_거듭제곱_재귀_D3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int tc = 1; tc <= 10; tc++) {
			int t = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			// n의 m제곱을 구해라.
			System.out.println("#" + tc + " " + solve(n, m));
		}
	}

	private static int solve(int n, int m) {
		if (m == 0)
			return 1;
		return n * solve(n, m - 1);
	}
}
