package boj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1520_내리막길_G4_이미요 {
	static int M, N;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] dp;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 세로길이(행 수)
		N = Integer.parseInt(st.nextToken()); // 가로길이(열 수)
		map = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료
		
		//dfs + dp로 푼다.
		dp = new int[M][N];				//dp[i][j]: map[i][j]에서 map[M-1][N-1]로 가는 경로의 수
		for (int i = 0; i < M; i++) {	//초기값을 -1로 설정한다.
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(0,0));
	}

	//r,c로부터 M-1,N-1으로 가는 경로의 갯수를 리턴하는 함수
	private static int dfs(int r, int c) {
		if(r==M-1 && c==N-1) {	//자기 자신으로 가는 경로 갯수는 1.
			return 1;
		}
		if(dp[r][c]==-1) {
			dp[r][c]=0;
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr>=0 && nr<M && nc>=0 && nc<N && map[nr][nc]<map[r][c]) {
					dp[r][c]+=dfs(nr,nc);	//각 방향으로 탐색했을때 경로 수를 더한다.
				}
			}
		}
		return dp[r][c];
	}
}
