package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_10026_적록색약_G5 {

	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
	}

	static int N;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static boolean[][] visit;
	static int count1 = 0;
	static int count2 = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = row.charAt(j);
			}
		}
		// 입력 완료

		// 일반인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					DFS(i, j);
					++count1;
				}
			}
		}

		// 적록색약 버전으로 맵을 바꿔준다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'G') {
					map[i][j] = 'R';
				}
			}
		}
		visit = new boolean[N][N]; // 초기화

		// 적록색약
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					DFS(i, j);
					++count2;
				}
			}
		}

		System.out.println(count1 + " " + count2);
	}

	private static void DFS(int r, int c) {
		visit[r][c]=true;
		for (int i = 0; i < 4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(nr>=0 && nr<N && nc>=0 && nc<N && !visit[nr][nc]) {
				if(map[r][c]==map[nr][nc]) {
					DFS(nr,nc);
				}
			}
		}
	}
}
