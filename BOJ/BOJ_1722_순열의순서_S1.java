package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1722_순열의순서_S1 {
	//원소 n개의 순열의 개수는 n!개.	
	static int game;
	static int n;
	static long k;
	static long[] f;

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine()); // 1<=n<=20

		st = new StringTokenizer(br.readLine());
		game = Integer.parseInt(st.nextToken()); // 소문제 번호 입력.

		f = new long[n + 1];	//원소가 n개일때, 만들수 있는 순열의 갯수
		Arrays.fill(f, 1);
		for (int i = 1; i <= n; i++) {
			f[i] = i * f[i - 1];
		}

		if (game == 1) {
			k = Long.parseLong(st.nextToken()); // k번째 순열을 구하라.
			sol1(new boolean[n + 1], new int[n]); // 0,1 이런식으로 가도록 -1해줌. (3번째 -> 2)
		} else {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			sol2(new boolean[n + 1], arr);
		}
		System.out.println(sb.toString());
	}

	
	private static void sol1(boolean[] visit, int[] arr) {
		//팩토리얼 가짓수 치기
		for (int i = 0; i < n; i++) {	//순열 한 자리씩 찾기
			for (int j = 1; j <= n; j++) {
				//j가 순열에 존재하지 않는 숫자라면
				if(!visit[j]) {
					// 팩토리얼 값이 k보다 작으면 k에서 팩토리얼 값을 빼준다
					if(f[n-i-1] < k) {
						k -= f[n-i-1];
					}else {
						//팩토리얼 값이 k보다 크거마 같으면 해당하는 원소를 찾은것.
						//arr[i]에 저장하고 순열에 존재하는 숫자 체크.
						arr[i] = j;
						visit[j] = true;
						break;
					}
				}
				
			}
		}
		for (int i = 0; i < n; i++) {
			sb.append(arr[i]).append(" ");
		}
	}

	//원소 n개의 순열의 개수는 n!개. f[n] = n!
	private static void sol2(boolean[] visit, int[] arr) {
		long ans = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <arr[i]; j++) {
				if(!visit[j]) {
					ans += f[n-i-1];
				}
			}
			visit[arr[i]]=true;
		}
		sb.append(ans);
	}

}
