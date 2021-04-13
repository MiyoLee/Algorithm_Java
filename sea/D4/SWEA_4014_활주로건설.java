package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설 {
	static int[][] map;
	static int[][] tmap;
	static int N, X;
	static int answer;
	static int[] dr = {0,1};	// 우 하
	static int[] dc = {1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // map 크기
			X = Integer.parseInt(st.nextToken()); // 경사로의 길이
			map = new int[N][N];
			tmap = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = tmap[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#"+tc+" "+process());
			
			//<구현>
			//1. 가로분할(행고정) 활주로 건설 가능 체크,카운팅
			//2. 세로분할(열고정) 활주로 건설 가능 체크,카운팅
			
		}
	}
	
	private static int process() {
		int answer=0;
		for (int i = 0; i < N; i++) {
			if(makeRoad(map[i])) ++answer;
			if(makeRoad(tmap[i])) ++answer;
		}
		return answer;
	}

	private static boolean makeRoad(int[] road) {
		int beforeHeight = road[0], size = 0;
		int j = 0;	//탐색 열 위치
		while(j<N) {
			if(beforeHeight == road[j]) {
				++size;
				++j;
			}else if(beforeHeight+1==road[j]) {	//한칸 높아졌다. 오르막 경사로 가능한지 체크
				if(size<X) return false;
				beforeHeight=road[j];
				size = 1;
				++j;
			}else if(beforeHeight-1 == road[j]) {	//한칸 낮아졌다. 내리막 경사로 가능한지 체크
				int count = 0;
				for(int k=j; k<N; k++) {
					if(road[k] != beforeHeight-1) break;
					if(++count==X)break;
				}
				if(count<X) return false;	//X개 만나기 전에 빠져나왔다면 경사로 설치 불가.
				//설치 가능한 상황
				beforeHeight--;
				size = 0;	
				j += X;		//경사로 놓은 다음 블럭 위치.
			}else {
				return false;
			}
		}
		return true;
	}
}