package ssafy_algo_0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전 {
	private static int M, A;
	private static int[][][] map;
	private static int[] dr = { 0, -1, 0, 1, 0 }; // 이동X 상 우 하 좌
	private static int[] dc = { 0, 0, 1, 0, -1 };
	private static BC[] bcs;

	static class BC {
		int x, y; // 위치
		int c; // 충전범위
		int p; // 처리량

		public BC(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 총 이동시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수

			// 이동 궤적 입력받기.
			int[] amove = new int[M + 1];
			int[] bmove = new int[M + 1];
			// 1. A의 이동 궤적 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				amove[i] = Integer.parseInt(st.nextToken());
			}
			// 2. B의 이동 궤적 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				bmove[i] = Integer.parseInt(st.nextToken());
			}

			map = new int[11][11][2]; // 지도, 겹치는경우 처리량 상위 2개 저장
			// BC의 정보 입력
			bcs = new BC[A + 1];
			bcs[0] = new BC(-1,-1,0,0);
			
			for (int bc = 1; bc <= A; bc++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken()); // 충전 범위
				int p = Integer.parseInt(st.nextToken()); // 처리량
				bcs[bc] = new BC(x, y, c, p);
				
				for (int i = y - c; i <= y + c; i++) {
					for (int j = x - c; j <= x + c; j++) {
						if (i >= 1 && i <= 10 && j >= 1 && j <= 10 && Math.abs(i - y) + Math.abs(j - x) <= c) {
							fillmap(i, j, bc, p);
						}
					}
				}
			}
			// 입력 완료

			int result = 0;
			int ax = 1, ay = 1;
			int bx = 10, by = 10;
			for (int i = 0; i <= M; i++) {
				ay += dr[amove[i]];
				ax += dc[amove[i]];
				by += dr[bmove[i]];
				bx += dc[bmove[i]];
				// 좌표 이동 완료.
				 
				// 둘중 하나라도 0일때
				if(map[ay][ax][0]==0 && map[by][bx][0]==0) continue;
				else if (map[ay][ax][0]==0)result+=bcs[map[by][bx][0]].p;
				else if (map[by][bx][0]==0)result+=bcs[map[ay][ax][0]].p;
				
				// 둘다 0이 아닐때
				if(map[ay][ax][0]>0 && map[by][bx][0]>0) { 
					if (map[ay][ax][0] != map[by][bx][0]) {
						result += bcs[map[ay][ax][0]].p + bcs[map[by][bx][0]].p;
					} else { // 선택한 BC가 겹칠경우
						int max = Math.max(bcs[map[ay][ax][0]].p + bcs[map[by][bx][1]].p,	
								bcs[map[ay][ax][1]].p + bcs[map[by][bx][0]].p);
						result+=max;
					}
				}
			}
			System.out.println("#"+tc+" "+ result);
		}
	}

	// 현재 위치가 [i][j]일때, 값을 채워넣는 함수.
	public static void fillmap(int i, int j, int bcNo, int p) {
		if (map[i][j][0] == 0)
			map[i][j][0] = bcNo; // 비어있을경우
		else if (map[i][j][1] == 0) { // 하나 채워져있을경우 더 큰 수가 [0]에.
			if (bcs[map[i][j][0]].p >= p )
				map[i][j][1] = bcNo;
			else {
				int tmp = map[i][j][0];
				map[i][j][0] = bcNo;
				map[i][j][1] = tmp;
			}
		} else { // 둘다 채워져있을경우
			if (bcs[map[i][j][0]].p <= p) { // 현재 p가 가장 클경우
				int tmp = map[i][j][0];
				map[i][j][0] = bcNo;
				map[i][j][1] = tmp;
			} else if (bcs[map[i][j][1]].p <= p)
				map[i][j][1] = bcNo;
		}
	}
}
