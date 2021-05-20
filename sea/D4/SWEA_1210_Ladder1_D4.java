package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1210_Ladder1_D4 {
	static int[][]map;
	static int sc;	//목적지의 열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			map = new int[100][100];
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i==99 && map[i][j]==2) sc = j;
				}
			}
			//100*100 사다리 입력 완료
			System.out.println("#"+tc+" "+solve(98, sc));
		}
	}
	// 출발지 열을 구하는 함수.
	private static int solve(int r, int c) {	//현재 위치의 행과 열.
		while(r>0) {
			while(r>0 && !((c-1>=0 && map[r][c-1]==1) || (c+1<100 && map[r][c+1]==1))) --r;
			
			if(c-1>=0 && map[r][c-1]==1) {	//좌로 쭉
				--c;
				while(c>=0 && (map[r-1][c]==0 && map[r+1][c]==0)) --c;
			}else if(c+1<100 && map[r][c+1]==1) {	//우로 쭉
				++c;
				while(c<100 && (map[r-1][c]==0 && map[r+1][c]==0)) ++c;
			}
			--r;
		}
//		while(r>0) {
//			if(c-1>=0 && map[r][c-1]==1) {	//좌로 쭉
//				--c;
//				while(c>=0 && (map[r-1][c]==0 && map[r+1][c]==0)) --c;
//			}else if(c+1<100 && map[r][c+1]==1) {	//우로 쭉
//				++c;
//				while(c<100 && (map[r-1][c]==0 && map[r+1][c]==0)) ++c;
//			}
//			--r;
//		}
		return c;
	}
}
