package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형방_D4 {
	static int N;
	static int[][] map;
	static int[][] dp;
	static int[] dr = {-1,0,1,0};	//상우하좌
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			//입력 완료
			int start=0;
			int max=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(dp[i][j]==0) {
						int rooms = dfs(i,j);
						if(max < rooms) {
							max = rooms;
							start = map[i][j];
						}else if(max==rooms && start > map[i][j]) {
							start = map[i][j];
						}
					}
				}
			}
			System.out.println("#"+tc+" "+start+" "+max);
		}
		
	}
	
	//map[r][c]에서 갈수 있는 최대 방 갯수를 리턴한다.
	private static int dfs(int r, int c) {
		if(dp[r][c]>0) return dp[r][c];
		dp[r][c] = 1;
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc]==map[r][c]+1) {
				dp[r][c] += dfs(nr,nc);
				break;
			}
		}
		return dp[r][c];
	}
}
