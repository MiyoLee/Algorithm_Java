package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크_S3_백트래킹 {
	static int n;
	static int[][] stat;
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
		min = Integer.MAX_VALUE;
		splitTeam(0, 0, new int[n/2]);
		System.out.println(min);
	}
	
	// 팀 나누기
	static void splitTeam(int cnt, int startIdx, int[] selected) {
		if(cnt==n/2) {
			int diff = Cal(selected);	//stat 계산
			if(diff < min) min=diff;
			return;
		}
		for (int i = startIdx; i < n; i++) {
			selected[cnt]=i;			
			splitTeam(cnt+1, i+1, selected);			
		}
	}
	
	static int Cal(int[] teamA) {
		boolean[] isSelected = new boolean[n];
		for (int i = 0; i < teamA.length; i++) {
			isSelected[teamA[i]]=true;
		}
		
		int[] teamB = new int[n/2];
		int index = 0;
		for (int i = 0; i < isSelected.length; i++) {
			if(!isSelected[i]) {
				teamB[index++]=i;
			}
		}
		
		// stat합 계산
		int a = 0;
		for (int i = 0; i < teamA.length; i++) {
			for (int j = 0; j < teamA.length; j++) {
				if(i!=j) a+=stat[teamA[i]][teamA[j]];
			}
		}
		
		int b = 0;
		for (int i = 0; i < teamB.length; i++) {
			for (int j = 0; j < teamB.length; j++) {
				if(i!=j) b+=stat[teamB[i]][teamB[j]];
			}
		}
		
		return Math.abs(a-b);
	}
}
