package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15683_감시_G5 {
	static class Cctv {
		int r, c, kind;

		public Cctv(int r, int c, int cctv) {
			super();
			this.r = r;
			this.c = c;
			this.kind = cctv;
		}
	}

	static int N, M, cnt;					//cnt: tv갯수 저장할 변수
	static Cctv[] tv = new Cctv[8];			//cctv는 최대 8개이다.
	static int min = Integer.MAX_VALUE;		//정답 저장할 변수
	static int[] dr = {-1,0,1,0};			//상 우 하 좌
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					tv[cnt++] = new Cctv(i, j, map[i][j]);
				}
			}
		}
		dfs(0, map);
		System.out.println(min);
	}

	static boolean dfs(int idx, int[][] map) {		//현재 탐색중인 cctv의 index, 현재 맵 상태
		if (idx == cnt) {
			min = Math.min(min, getZero(map));
			if(min==0) return true;
			return false;
		}
		Cctv cur = tv[idx];						//현재 탐색할 cctv
		switch(cur.kind) {
		case 1: 
			for (int d = 0; d < 4; d++) {
				int[][] newMap = copy(map);
				watch(d, cur.r, cur.c, newMap);
				if(dfs(idx+1, newMap)) return true;
			} break;
		case 2:
			for (int i = 0; i < 2; i++) {	//방향 경우의 수
				int[][] newMap = copy(map);
				watch(i, cur.r, cur.c, newMap);	
				watch(i+2, cur.r, cur.c, newMap);
				if(dfs(idx+1, newMap)) return true;
			} break;
		case 3:
			for (int i = 0; i < 4; i++) {	//방향 경우의 수
				int[][] newMap = copy(map);
				watch(i, cur.r, cur.c, newMap);	
				watch((i+1)%4, cur.r, cur.c, newMap);
				if(dfs(idx+1, newMap)) return true;
			} break;
		case 4:
			for (int i = 0; i < 4; i++) {	//방향 경우의 수
				int[][] newMap = copy(map);
				watch(i, cur.r, cur.c, newMap);	
				watch((i+1)%4, cur.r, cur.c, newMap);
				watch((i+2)%4, cur.r, cur.c, newMap);
				if(dfs(idx+1, newMap)) return true;
			} break;
		case 5:
			int[][] newMap = copy(map);
			for (int d = 0; d < 4; d++) {
				watch(d, cur.r, cur.c, newMap);	
			}
			if(dfs(idx+1, newMap)) return true;
			break;
		}
		return false;
	}

	//map[r][c]에서 d 방향으로 쭉 감시 영역 표시하는 함수
	private static void watch(int d, int r, int c, int[][] map) {
		int nr=r+dr[d], nc=c+dc[d];
		while(isRange(nr,nc) && map[nr][nc]!=6) {	//범위 안에 있고 벽을 만나기 전까지 
			if(map[nr][nc]==0) map[nr][nc]=-1;		//감시 영역 -1로 체크
			nr+=dr[d];
			nc+=dc[d];
		}
	}

	//범위 안에 있는지 체크하는 함수
	static boolean isRange(int r, int c) {	
		return r >= 0 && c >= 0 && N > r && M > c;
	}

	// map을 copy한 newMap을 리턴하는 함수
	static int[][] copy(int[][] map) {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}
	
	//map에서 현재 0의 갯수 리턴하는 함수
	static int getZero(int[][] map) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					count++;
			}
		}
		return count;
	}
}
