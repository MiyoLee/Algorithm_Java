package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2564_경비원_S1 {
	static int width, height;
	static int n; // 상점 갯수
	static int[][] store; // 상점 위치
	static int r,c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		width = Integer.parseInt(st.nextToken()); // 가로길이
		height = Integer.parseInt(st.nextToken()); // 세로길이
		n = Integer.parseInt(br.readLine());

		store = new int[n+1][2]; // 0~n-1 : 상점의 위치정보 	n:동근이 위치정보

		for (int i = 0; i <= n; i++) { // 상점 위치 , 동근이의 위치 입력
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			switch(dir) {
			case 1: store[i][0]=0; store[i][1]=dist; break;
			case 2: store[i][0]=height; store[i][1]=dist; break;
			case 3: store[i][0]=dist; store[i][1]=0; break;
			case 4: store[i][0]=dist; store[i][1]=width; break;
			default:;
			}
		}
		
		r = store[n][0];
		c = store[n][1];
		int sum = 0;
		for(int i=0; i<n; i++) {
			if((r==store[i][0]) && (r==0 || r==height)) {
				sum+=Math.abs(c - store[i][1]);
			}
			else if((c==store[i][1]) && (c==0 || c==width)) {
				sum+=Math.abs(r - store[i][0]);
			}
			else if(Math.abs(r-store[i][0])==height) {		//북-남
				sum+=height+Math.min(c+store[i][1], width-c+width-store[i][1]);
			}
			else if(Math.abs(c-store[i][1])==width) {		//서-동
				sum+=width+Math.min(r+store[i][0], height-r+height-store[i][0]);
			}
			else {
				sum+=Math.abs(r-store[i][0])+Math.abs(c-store[i][1]);
			}
		}
		System.out.println(sum);
	}

}
