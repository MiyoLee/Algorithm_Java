package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
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
		for (int tc = 1; tc <= T; tc++) {
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
			bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void bfs() {
		int time = 0;

		visited[R][C] = true;
		q.offer(new Pos(R, C));

		while (!q.isEmpty()) {
			int size = q.size();
			if (++time >= L) return;
			
			for (int i = 0; i < size; i++) {
				Pos cur = q.poll();
				int r = cur.r;
				int c = cur.c;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc]!=0) {
						switch (d) {
						case 0: // 위쪽 방향 체크한다면
							if ((map[r][c] == 1 || map[r][c] == 2 || map[r][c] == 4 || map[r][c] == 7)
									&& (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6)) {
								q.offer(new Pos(nr, nc));
								visited[nr][nc] = true;
								++ans;
							}
							break;
						case 1: // 아래쪽 방향 체크한다면
							if ((map[r][c] == 1 || map[r][c] == 2 || map[r][c] == 5 || map[r][c] == 6)
									&& (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7)) {
								q.offer(new Pos(nr, nc));
								visited[nr][nc] = true;
								++ans;
							}
							break;
						case 2: // 왼쪽 방향 체크한다면
							if ((map[r][c] == 1 || map[r][c] == 3 || map[r][c] == 6 || map[r][c] == 7)
									&& (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5)) {
								q.offer(new Pos(nr, nc));
								visited[nr][nc] = true;
								++ans;
							}
							break;
						case 3: // 오른쪽 방향 체크한다면
							if ((map[r][c] == 1 || map[r][c] == 3 || map[r][c] == 4 || map[r][c] == 5)
									&& (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7)) {
								q.offer(new Pos(nr, nc));
								visited[nr][nc] = true;
								++ans;
							}
							break;
						default: break;
						}
					}
				}
			}

		}
	}
}
