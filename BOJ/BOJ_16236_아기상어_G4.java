package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_16236_아기상어_G4 {

	static class Pos implements Comparable<Pos>{
		int r;
		int c;
		int size;
		int eat;
		int time;
		
		Pos(int r, int c, int size, int eat, int time){
			this.r=r;
			this.c=c;
			this.size=size;
			this.eat=eat;
			this.time=time;
		}
		@Override
		public String toString() {
			return "("+r+","+c+") 크기:"+size+" 먹은물고기:"+eat+" 지난시간:"+time;
		}
		@Override
		public int compareTo(Pos o) {
			
			if(o.time==this.time){
				int diff = this.r-o.r;
				return diff!=0 ? diff : this.c-o.c;
			}
			else return this.time-o.time;
		}
	}
	
	static int N;	//공간 크기
	static int F=0;	//물고기 수
	static int[][] map;
	static Pos shark;
	static int dr[] = {-1,0,0,1};	//상좌우하
	static int dc[] = {0,-1,1,0};	//상좌우하
	static int ans=0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		Pos start = null;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					start = new Pos(i,j,2,0,0);
					map[i][j]=0;
				}else if(map[i][j]>0) {
					++F;
				}
			}
		}
		//입력완료
		
		shark=start;
	
		for (int i = 0; i < F; i++) {
			BFS(shark, new boolean[N][N]);
			if(ans < shark.time)ans = shark.time;
		}
		
		System.out.println(ans);
	}


	//가장 가까운 물고기 한마리 잡아먹는 함수.
	private static void BFS(Pos start, boolean[][]visit) {
		visit[start.r][start.c]=true;	//시작점 방문체크
		
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(start);
		
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
		
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = pos.r+dr[i];
				int nc = pos.c+dc[i];
				if(nr>=0 && nr<N && nc>=0 && nc<N && !visit[nr][nc]&& map[nr][nc]<=pos.size) {
					
					if(map[nr][nc]>0 && pos.size > map[nr][nc]) {	//잡아먹을 수 있는 물고기 발견.
						
						if(pos.eat+1==pos.size) {	//상어 크기 숫자만큼 물고기 먹었다면 상어크기+1
							pq.add(new Pos(nr,nc,pos.size+1,0,pos.time+1));
						}else {		//먹은물고기수+1
							pq.add(new Pos(nr,nc,pos.size,pos.eat+1,pos.time+1));
						}
						break;
					}
					else{	 //그냥 지나간다.
						visit[nr][nc]=true;
						q.add(new Pos(nr,nc,pos.size,pos.eat,pos.time+1));
					}
				}
			}
		}
		
		if(!pq.isEmpty()) {
			map[pq.peek().r][pq.peek().c] = 0;
			shark = pq.peek();
		}
	}
}
