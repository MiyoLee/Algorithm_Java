package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크_S3_백트래킹 {
	static int n;
	static int[][] stat;
	static boolean[] visit;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());	//짝수
		stat = new int[n][n];				//능력치 배열
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				stat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료
		visit = new boolean[n];
		min = Integer.MAX_VALUE;
		Comb(0, 0);
		System.out.println(min);
	}
	
	// 팀 나누기
	static void Comb(int cnt, int startIdx) {
		if(cnt==n/2) {
			int diff = Cal();	//stat 차이 계산
			if(diff < min) min=diff;
			return;
		}
		for (int i = startIdx; i < n; i++) {
			visit[i]=true;		
			Comb(cnt+1, i+1);
			visit[i]=false;
		}
	}
	
	static int Cal() {
		
		int a=0;
		int b=0;
		
		for (int i = 0; i < visit.length; i++) {
			for (int j = i+1; j < visit.length; j++) {
				if(visit[i] && visit[j]) a+=stat[i][j]+stat[j][i];
				else if(!visit[i] && !visit[j]) b+=stat[i][j]+stat[j][i];
			}
		}
		
		int val =  Math.abs(a-b);
		if(val==0) {
			System.out.println(val);
			System.exit(0);
		}
		return val;
	}
}
