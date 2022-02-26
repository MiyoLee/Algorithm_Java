package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달_G5 {
	
	static class Pos{
		int r,c;
		
		Pos(int r, int c){
			this.r=r;
			this.c=c;
		}
		
		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + "]";
		}
		
	}
	
	static int N,M;
	static int[][] map;
	static List<Pos>chicken = new ArrayList<>();
	static List<Pos>house = new ArrayList<>();
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					house.add(new Pos(i,j));
				}
				else if(map[i][j]==2) {
					chicken.add(new Pos(i,j));	//치킨 위치 리스트에 add
				}
			}
		}
		// 입력 완료
		// 0은 빈 칸, 1은 집, 2는 치킨집. 치킨집 개수 <= 13
		// 치킨집 M개를 고르자
	
		Comb(0, new Pos[M], 0);
		System.out.println(ans);
	}
	
	static void Comb(int cnt, Pos[] selected, int start) {		
		if(cnt==M) {
			int sumOfChickenDist = getSumOfChickenDist(selected); //치킨거리의 합 계산
			if(ans > sumOfChickenDist) {
				ans = sumOfChickenDist;
			}	
			return;
		}
		
		for (int i = start; i < chicken.size(); i++) {
			selected[cnt] = chicken.get(i);
			Comb(cnt+1, selected, i+1);
		}
	}

	private static int getSumOfChickenDist(Pos[] selectedChicken) {
		int sumOfChickenDist = 0;

		for (int hi = 0; hi < house.size(); hi++) {
			int chickenDist = Integer.MAX_VALUE;
			
			for (int ci = 0; ci < selectedChicken.length; ci++) {	//선택받은 치킨집 순회
				int dist = getDist(house.get(hi),selectedChicken[ci]);
				if(dist < chickenDist) {
					chickenDist = dist;
				}
			}
			sumOfChickenDist+=chickenDist;
		}
		
		return sumOfChickenDist;
	}

	private static int getDist(Pos pos1, Pos pos2) {
		return Math.abs(pos1.r-pos2.r)+Math.abs(pos1.c-pos2.c);
	}

}
