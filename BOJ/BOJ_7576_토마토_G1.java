package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576_토마토_G1 {
	static int M,N; //가로,세로
	static int[][] map;	//토마토상자
	static int[] dr = {-1,0,1,0};	//상좌하우 
	static int[] dc = {0,-1,0,1};	//상좌하우
	static boolean[][]visit;
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int ripenCnt=0;	//익은 토마토 갯수
	static int tomatoCnt=0; //총 토마토 갯수
	static int depth = 0;
	static int ans = 0;
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0 || map[i][j]==1) {	//토마토 갯수 세기
					++tomatoCnt;
				}
				if(map[i][j]==1) {	//익은 토마토의 위치를 큐에 넣는다.
					queue.add(new int[] {i,j,0});
					++ripenCnt;
				}
			}
		}
		//입력 완료
		visit = new boolean[N][M];
		BFS();
		System.out.println(ans);
	}

	private static void BFS() {
		if(ripenCnt==tomatoCnt) {
			ans=0;
			return;
		}
		while(!queue.isEmpty()) {
			if(ripenCnt==tomatoCnt) {
				ans = depth+1;
				return;
			}
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			depth = cur[2];
			
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visit[nr][nc] && map[nr][nc]==0) {
					visit[nr][nc]=true;
					map[nr][nc]=1;
					++ripenCnt;
					queue.add(new int[] {nr,nc,depth+1});
				}
			}
		}
		
		ans = -1;
		return;
	}
}
