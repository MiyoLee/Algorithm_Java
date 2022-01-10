package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기_S1_백트래킹 {
	static int n;
	static int[] numbers;
	static int[] ops;
	static long min = Long.MAX_VALUE;
	static long max = Long.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());

		numbers = new int[n];
		ops = new int[4];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			ops[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 완료
		Per(0, new int[n - 1]);

		System.out.println(max);
		System.out.println(min);
	}

	// 연산자 순열
	private static void Per(int cnt, int[] selected) {
		if (cnt == n - 1) {	//연산자 순열 완성
//			System.out.println(Arrays.toString(selected));
			long result = calc(selected);
			if (result < min)
				min = result;
			if (result > max)
				max = result;
			return;
		}

		for (int j = 0; j < 4; j++) {
			if (ops[j] > 0) {
				selected[cnt]=j;
				--ops[j];
				Per(cnt + 1, selected);
				++ops[j];
			}
		}

	}

	private static long calc(int[] selected) {
		int result = numbers[0];
		for (int i = 0; i < selected.length; i++) {
			switch (selected[i]) {
			case 0:
				result += numbers[i + 1]; break;
			case 1:
				result -= numbers[i + 1]; break;
			case 2:
				result *= numbers[i + 1]; break;
			case 3:
				result /= numbers[i + 1]; break;
			default:
				result = -1; break;
			}
		}
		return result;
	}
}
