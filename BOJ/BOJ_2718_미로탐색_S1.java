package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2718_미로탐색_S1 {

	static int N,M;
	static int[][] map;
	static boolean[][] visit;
	static int dr[] = {1,0,-1,0};	//하우상좌
	static int dc[] = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		visit = new boolean[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = tmp.charAt(j-1)-'0';
			}
		}
		// 입력 완료
		bfs();
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {1,1,1});
		visit[1][1] = true;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int r = pos[0];
			int c = pos[1];
			int level = pos[2];
			
			if(r==N && c==M) {
				System.out.println(level);
				System.exit(0);
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(nr>=1 && nr<=N && nc>=1 && nc<=M && !visit[nr][nc] && map[nr][nc]==1) {
					visit[nr][nc]=true;
					q.add(new int[] {nr,nc,level+1});
				}
			}
		}
	}
}
