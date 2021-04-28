package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토_S1 {
	static class Pos{
		int h,r,c;

		public Pos(int h, int r, int c) {
			super();
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}
	static int N,M,H;
	static int[][][] map;
	static Queue<Pos>q;
	static int tomato;
	static int ans;
	static int[] dr = {-1,1,0,0,0,0};	//상하좌우 위 아래
	static int[] dc = {0,0,-1,1,0,0};
	static int[] dh = {0,0,0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	//상자 가로 길이
		N = Integer.parseInt(st.nextToken());	//상자 세로 길이
		H = Integer.parseInt(st.nextToken());	//상자 갯수
		map = new int[H][N][M];
		q = new LinkedList<>();
		tomato = 0;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if(map[h][i][j]==0 || map[h][i][j]==1) {
						++tomato;
						if(map[h][i][j]==1) {
							q.offer(new Pos(h,i,j)); 		//익은 토마토는 큐에 집어넣음.
						}
					}
				}
			}
		}
		//입력 완료.
		//bfs로 구하자.
		ans=0;
		bfs();
		System.out.println(ans);
	}
	public static void bfs() {
		int cnt = q.size();		//익은 토마토 갯수
		int level = 1;
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 6; d++) {
				int nh = cur.h+dh[d];
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(nh>=0 && nh<H && nr>=0 && nr<N && nc>=0 && nc<M && map[nh][nr][nc]==0) {
					level = map[nh][nr][nc] = map[cur.h][cur.r][cur.c]+1;
					q.offer(new Pos(nh,nr,nc));
					++cnt;
				}
			}
		}
		if(cnt < tomato) ans=-1;
		else if(cnt == tomato) ans = level-1;
	}
	public static void print() {
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(map[h][i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("============================");
		}
	}
}
