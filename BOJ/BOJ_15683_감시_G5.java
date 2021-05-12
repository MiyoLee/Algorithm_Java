package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15683_감시_G5 {
	static class Cctv {
		int r, c, cctv;

		public Cctv(int r, int c, int cctv) {
			super();
			this.r = r;
			this.c = c;
			this.cctv = cctv;
		}
	}


	static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int[][] dir = {
            {},
            {1},
            {1, 3},
            {0, 1},
            {0, 1, 3},
            {0, 1, 2, 3}
    };
	static int n, m, cnt;
	static Cctv[] tv = new Cctv[8];
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new StringReader(src));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] > 0 && map[i][j] < 6) {
					tv[cnt++] = new Cctv(i, j, map[i][j]);
				}
			}
		}
		
		DFS(0, map);
		System.out.println(min);
	}

	static void DFS(int idx, int[][] map) {
		if (idx == cnt) {
//			for(int i =0 ;i<n; i++) {
//				for(int j =0; j<m; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			int count=0;
			for(int i =0 ;i<n; i++) {
				for(int j =0; j<m; j++) {
					if(map[i][j]==0)
						count++;
				}	
			}
			min = Math.min(min, count);
			return;
		}
		Cctv top = tv[idx];
		for (int d = 0; d < 4; d++) {
			int[][] copymap = copy(map);
			for (int i = 0; i < dir[top.cctv].length; i++) {
				int newR = top.r;
				int newC = top.c;
				int newD = (dir[top.cctv][i]-d+3)%4;   /////////////
				
				while(true) {
					newR +=dirs[newD][0];
					newC +=dirs[newD][1];
					//break 걸어야함 , 맵 나갔을때, 6만났을떄
					if(!isRange(newR, newC) || map[newR][newC]==6) break;
					copymap[newR][newC] = 9;
				}
			}
			DFS(idx+1, copymap);
		}
	}
	static boolean isRange(int r, int c) {
		return r>=0 && c>=0 && n>r && m>c;
	}

	static int[][] copy(int[][] map) {
		int[][] copymap = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <m; j++) {
				copymap[i][j] = map[i][j];
			}
		}
		return copymap;
	}

	static String src = "4 5\r\n" + 
			"0 0 0 6 0\r\n" + 
			"6 0 6 0 0 \r\n" + 
			"2 6 0 0 0\r\n" + 
			"0 0 6 0 3";
}
