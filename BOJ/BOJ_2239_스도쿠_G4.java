package ssafy_algo_0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2239_스도쿠_G4 {
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int[][] board;
	static int blankCnt = 0;
	static int[][] rvisited;
	static int[][] cvisited;
	static int[][] gvisited;
	static List<Pos> blanks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[9][9];
		rvisited = new int[9][10];
		cvisited = new int[9][10];
		gvisited = new int[9][10];
		blanks = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = tmp.charAt(j) - '0';
				if (board[i][j] == 0) {
					++blankCnt;
					blanks.add(new Pos(i, j));
				} else {
					rvisited[i][board[i][j]] = 1; // i번째 행에서 해당값 방문표시
					cvisited[j][board[i][j]] = 1;
					int g = getGroupNo(i, j);
					gvisited[g][board[i][j]] = 1;
				}
			}
		}
		// 입력 완료.
		dfs(0);
		print();
	}

	private static int getGroupNo(int i, int j) {
		if (i >= 0 && i < 3 && j >= 0 && j < 3)
			return 0;
		if (i >= 0 && i < 3 && j >= 3 && j < 6)
			return 1;
		if (i >= 0 && i < 3 && j >= 6 && j < 9)
			return 2;
		if (i >= 3 && i < 6 && j >= 0 && j < 3)
			return 3;
		if (i >= 3 && i < 6 && j >= 3 && j < 6)
			return 4;
		if (i >= 3 && i < 6 && j >= 6 && j < 9)
			return 5;
		if (i >= 6 && i < 9 && j >= 0 && j < 3)
			return 6;
		if (i >= 6 && i < 9 && j >= 3 && j < 6)
			return 7;
		if (i >= 6 && i < 9 && j >= 6 && j < 9)
			return 8;
		return -1;
	}

	public static boolean dfs(int cnt) {
		// board[r][c]가 속한 열,그룹 검사.

		if (cnt == blankCnt) { // 스도쿠 판 다 채웠다
			return true;
		}
		
		int r = blanks.get(cnt).r;
		int c = blanks.get(cnt).c;
		for (int n = 1; n <= 9; n++) {
			if (rvisited[r][n] == 0) {// 행검사
				rvisited[r][n]++;
				cvisited[c][n]++;
				int g = getGroupNo(r, c);
				gvisited[g][n]++;
				board[r][c] = n;
				if(check(r,c)) {
					if(dfs(cnt + 1))return true;;
				}
				rvisited[r][n]--;
				cvisited[c][n]--;
				gvisited[g][n]--;
			}
		}
		return false;
	}

	// board[r][c]가 속한 열,그룹 검사.
	private static boolean check(int r, int c) {
		if (cvisited[c][board[r][c]] > 1)
			return false;
		
		int g = getGroupNo(r, c);
		if (gvisited[g][board[r][c]] > 1)
			return false;
		
		return true;
	}

	public static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
}
