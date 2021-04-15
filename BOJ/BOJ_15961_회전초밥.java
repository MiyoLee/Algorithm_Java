package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15961_회전초밥 {
	static int N,d,k,c;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//접시의 수
		d = Integer.parseInt(st.nextToken());	//가능한 초밥의 가짓수
		k = Integer.parseInt(st.nextToken());	//연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken());	//쿠폰에 적힌 번호
		
		int[] belt = new int[N];
		for (int i = 0; i < N; i++) {
			belt[i]=Integer.parseInt(br.readLine());
		}
		//입력 완료.
		max=0;
		int[] visited = new int[d+1];	//1~d초밥
		visited[c] = 1;
		int count = 1;	//k개 와 c 선택했을때 가짓수
		
		//첫 k개 선택한 경우
		for (int i = 0; i < k; i++) {
			if(visited[belt[i]]==0) {
				count++;
			}
			visited[belt[i]]++;
			if(max < count)max = count;
		}
		if(--visited[belt[0]]==0)count--;
		
		for (int i = k; i < N+k-1; i++) {		//i : 끝 인덱스.
			int ti = i;
			if(i>=N) ti = i%N;
			if(visited[belt[ti]]==0) {
				count++;
			}
			visited[belt[ti]]++;
			if(max < count)max = count;
			if(--visited[belt[i-k+1]]==0)count--;
		}
		System.out.println(max);
	}	
}
