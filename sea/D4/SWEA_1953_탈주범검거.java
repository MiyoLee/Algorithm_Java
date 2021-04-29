package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
	static class Pos {
		int r, c, t;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public Pos(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pos> q;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= 1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 세로크기
			M = Integer.parseInt(st.nextToken()); // 가로크기
			R = Integer.parseInt(st.nextToken()); // 맨홀뚜껑 세로위치
			C = Integer.parseInt(st.nextToken()); // 맨홀뚜껑 가로위치
			L = Integer.parseInt(st.nextToken()); // 탈출후 소요된 시간
			map = new int[N][M];
			visited = new boolean[N][M];
			q = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력 완료
			ans = 1;
			q.offer(new Pos(R, C, 1));
			bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void bfs() {
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			switch (map[cur.r][cur.c]) {
			case 1:	//상하좌우
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
						visited[nr][nc]=true;
						if ((d == 0 && (map[nr][nc] == 2 || map[nr][nc] == 5))
								|| (d == 1 && (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7))
								|| (d == 2 && (map[nr][nc] == 3 || map[nr][nc] == 4))
								|| (d == 3 && (map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7))) {
							
							q.offer(new Pos(nr,nc,cur.t+1));
							++ans;
						}
					}
				} break;
			case 2:	//상하
				for (int d = 0; d < 2; d++) {
					int nr = cur.r+dr[d];
					int nc = cur.c+dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
						visited[nr][nc]=true;
						if((d == 0 && (map[nr][nc] == 2 || map[nr][nc] == 5))
								|| (d == 1 && (map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7))){
							q.offer(new Pos(nr,nc,cur.t+1));
							++ans;
						}
					}
				} break;
			case 3: //좌우
				for (int d = 2; d < 4; d++) {
					int nr = cur.r+dr[d];
					int nc = cur.c+dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
						visited[nr][nc]=true;
						if((d == 2 && (map[nr][nc] == 3 || map[nr][nc] == 4))
								|| (d == 3 && (map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7))){
							q.offer(new Pos(nr,nc,cur.t+1));
							++ans;
						}
					}
				} break;
			}
			// ...
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("============================");
	}
}
