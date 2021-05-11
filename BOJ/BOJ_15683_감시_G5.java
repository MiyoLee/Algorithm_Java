package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15683_감시_G5 {
	static int N, M;
	static int[][] map;
	static List<Pos> cctvs;
	static int[] dr = { -1, 0, 1, 0 };	//상우하좌
	static int[] dc = { 0, 1, 0, -1 };
	static int answer;

	static class Pos {
		int r, c;
		int num;

		public Pos(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로크기
		M = Integer.parseInt(st.nextToken()); // 가로크기
		map = new int[N][M];
		cctvs = new ArrayList<>(); // cctv의 좌표와 번호 저장할 배열

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5)
					cctvs.add(new Pos(i, j, map[i][j]));
			}
		}
		// 입력 완료.
		answer = Integer.MAX_VALUE;
		dfs(0, map);
		System.out.println(answer);
	}

	private static boolean dfs(int index, int[][] map) { // 현재 몇번째 cctv 인지, 현재 맵 상황
		int zero = getZeros(map);
		if(zero==0) {
			answer=0;
			return true;
		}
		if (index == cctvs.size()) { // 모든 cctv다 검사 했다.
			answer = Math.min(getZeros(map), answer);
			return false;
		}

		Pos cur = cctvs.get(index); // 현재 cctv의 정보
		int dirCase = 0;
		switch (cur.num) {
		case 1:
			dirCase = 4;
			break;
		case 2:
			dirCase = 2;
			break;
		case 3:
			dirCase = 4;
			break;
		case 4:
			dirCase = 4;
			break;
		case 5:
			dirCase = 1;
			break;
		}
		for (int dirN = 0; dirN < dirCase; dirN++) {
			int[][] newMap = new int[N][M];
			copy(newMap, map);

			bfs(cur, dirN, newMap);
			if(dfs(index + 1, newMap)) return true;
			// 리턴하면 map도 원래대로 돌아간다.
			
		}
		return false;
	}

	// newMap에 map을 복사하는 함수
	private static void copy(int[][] newMap, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

	private static void bfs(Pos cur, int dirN, int[][] map) {
		if (cur.num == 5) {
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				while (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 6) {
					if(map[nr][nc]==0) map[nr][nc] = -1;
					nr += dr[d];
					nc += dc[d];
				}
			}
		} else if (cur.num == 1) {
			int nr = cur.r + dr[dirN];
			int nc = cur.c + dc[dirN];
			while (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 6) {
				if(map[nr][nc]==0) map[nr][nc] = -1;
				nr += dr[dirN];
				nc += dc[dirN];
			}
		} else if (cur.num == 2) {
			// dirN:0 -> 상하, dirN:1 -> 좌우
			for (int d = dirN; d <= dirN+2; d+=2) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				while (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 6) {
					if(map[nr][nc]==0) map[nr][nc] = -1;
					nr += dr[d];
					nc += dc[d];
				}
			}
		} else if (cur.num == 3) { // 직각으로 두방향
			// dirN:0 -> 상우, dirN:1 -> 하좌, dirN:2 -> 좌상, dirN:3 -> 우하
			for (int d = dirN; d <= dirN+1; d++) {
				d%=4;
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				while (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 6) {
					if(map[nr][nc]==0) map[nr][nc] = -1;
					nr += dr[d];
					nc += dc[d];
				}
			}
		} else if (cur.num == 4) { // 직각으로 두방향
			for (int d = 0; d < 4; d++) {
				if (d == dirN)
					continue;
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				while (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 6) {
					if(map[nr][nc]==0) map[nr][nc] = -1;
					nr += dr[d];
					nc += dc[d];
				}
			}
		}
	}

	// map에서 0의 갯수 구하는 함수
	private static int getZeros(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 0)
					++cnt;
			}
		}
		return cnt;
	}

}
