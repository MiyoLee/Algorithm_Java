package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14425_문자열집합_S3 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N; // N개의 문자열로 이루어진 집합 S 한개 존재.
		String[] S;
		int M; // M개의 문자열이 주어짐.
		String[] input;
		int count = 0; // S에 포함된 문자열 개수

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		S = new String[N]; // N개의 문자열로 이루어진 집합 S
		input = new String[M];
		
		for (int i = 0; i < N; i++) { // 집합 S 입력
			S[i] = br.readLine();
		}
		Arrays.sort(S); // 알파벳순으로 정렬

		for (int i = 0; i < M; i++) {	// input 배열 입력
			input[i] = br.readLine();
		}
		Arrays.sort(input);	//알파벳순으로 정렬
		
		int i=0,j=0;	// i: input[]의 인덱스,  j: S[]의 인덱스
		while(i<M && j<N) {
			if(input[i].equals(S[j])) {
				++count;
				++i;
			}else if(input[i].compareTo(S[j]) > 0) {
				++j;
			}else {
				++i;
			}
		}

		// M개의 문자열 중에 총 몇 개가 집합 S에 포함되어 있는지 출력
		System.out.println(count);

	}
	
}
