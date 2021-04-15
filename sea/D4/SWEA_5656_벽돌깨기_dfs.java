package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기_dfs {
	static int N,W,H;
	static int[][] map;
	static int[][] tmap;
	static int min;
	static int totalCnt;
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); 	// 구슬 던질 횟수
			W = Integer.parseInt(st.nextToken());	//map의 가로길이
			H = Integer.parseInt(st.nextToken());	//map의 세로길이
			map = new int[H][W];
			tmap = new int[H][W];
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					tmap[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]>0)totalCnt++;
				}
			}
			//입력 완료
			min=Integer.MAX_VALUE;
			Per(0, new int[N]);
			System.out.println("#"+tc+" "+min);
		}
		
	}
	//중복순열. N번 구슬 던지는 자리 순서
	public static void Per(int cnt, int[]selected) {
		if(cnt==N) {		//다 골랐다
			init();
			count = 0;
			for(int i=0; i<N; i++) {
				go(selected[i]);
			}
			//남은 블럭수 구하기
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if(map[i][j]>0)count++;
				}
			}
			//System.out.println(Arrays.toString(selected) + " : "+ count);
			if(count < min)min=count;
			return;
		}
		for (int i = 0; i < W; i++) {
			selected[cnt]=i;
			Per(cnt+1, selected);
		}
	}
	
	//c열에 구슬을 던졌을때 게임 진행.
	private static void go(int c) {
		int r = 0;
		while(r<H && map[r][c]==0)r++;
		// map[h][c] 명중!
		if(r<H) dfs(r,c);
		else return;
		// 빈공간 메꾸기
		for (int j = 0; j < W; j++) {
			int idx=H-1;
			for (int i = idx; i >= 0; i--) {
				while(i>=0 && map[i][j]==0)i--;
				if(i==-1)break;	//메꾸기 끝
				if(idx!=i) {
					map[idx][j]=map[i][j];
					map[i][j]=0;
				}
				idx--;
			}
		}	
	}
	
	//map[r][c]에 명중시켰을때 벽돌을 깨는 함수
	private static void dfs(int r, int c) {
		if(map[r][c]==1) {
			map[r][c]=0;
			return;
		}
		//세로
		int size = map[r][c];
		map[r][c] = 1;	//1로 친다.
		for (int i = r-size+1; i <= r+size-1; i++) {
			if(i>=0 && i<H && map[i][c]>0) dfs(i,c);
		}
		//가로
		for (int j = c-size+1; j <= c+size-1; j++) {
			if(j>=0 && j<W && map[r][j]>0) dfs(r,j);
		}
	}	
	//map을 초기화하는 함수.
	private static void init() {
		for(int i=0; i<map.length; i++) {
			map[i] = Arrays.copyOf(tmap[i], tmap[i].length);
		}
	}
}
