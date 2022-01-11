package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class BOJ_2580_스도쿠_G4_백트래킹 {
	
	public static class Pos{
		int r,c;
		Pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map = new int[9][9];
	static ArrayList<Pos> blanks = new ArrayList<Pos>();	// 빈칸의 위치 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) blanks.add(new Pos(i,j));
			}
		}
		// 입력 완료
		Solve(0, 0);
	}

	private static void Solve(int cnt, int posIdx) {
		if(cnt==blanks.size()) {
			print();
			System.exit(0);
		}
		int cr = blanks.get(posIdx).r;
		int cc = blanks.get(posIdx).c;
		for (int i = 1; i <= 9; i++) {
			if(!isDuplicated(i, cr,cc)) {
				map[cr][cc]=i;
				Solve(cnt+1, posIdx+1);
				map[cr][cc]=0;	//!!!!이거 빼먹으면 안됌!! 다른 칸 검사할때 이 칸도 고려하기 때문!!!!
			}
		}
	}

	// 현재위치 기준 가로, 세로, 같은 블럭에 같은 숫자가 이미 있으면 true
	private static boolean isDuplicated(int n, int cr, int cc) {
		// 1. 가로
		for (int j = 0; j < 9; j++) {
			if(j==cc) continue;
			if(map[cr][j]==n) return true;
		}
		// 2. 세로
		for (int i = 0; i < 9; i++) {
			if(i==cr) continue;
			if(map[i][cc]==n) return true;
		}
		// 3. 한 블럭
		for (int i = cr/3*3; i < cr/3*3+3; i++) {
			for (int j = cc/3*3; j < cc/3*3+3; j++) {
				if(i==cr && j==cc) continue;
				if(map[i][j]==n) return true;
			}
		}
		return false;
	}
	
	private static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
