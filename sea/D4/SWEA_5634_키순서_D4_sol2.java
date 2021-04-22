package com.ssafy.hwalgo31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5634_키순서_D4 {
	static int N,M;
	static boolean[][] adj;
	static int[] gtCnt, ltCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adj = new boolean[N+1][N+1];
			gtCnt = new int[N+1];
			ltCnt = new int[N+1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int g = Integer.parseInt(st.nextToken());
				adj[l][g]=true;
			}
			//입력 완료. 1->2 : 1보다 2가 크다
			//자신의 키가 몇번째인지 알 수 있는 학생? 나보다 작은 사람 + 나보다 큰사람 = N-1일때.
			int answer=0;
			for (int k = 1; k <= N; k++) {
				gtDFS(k, k, new boolean[N+1]);
			}
			for (int i = 1; i <= N; i++) {
				if(gtCnt[i]+ltCnt[i] == N-1) answer++;
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
	
	// 자기 자신보다 큰 사람이 몇명인지 리턴하는 함수
	private static void gtDFS(int cur, int src, boolean[] visited) {
		visited[cur]=true;
		for (int i = 1; i <= N; i++) {
			if(!visited[i] && adj[cur][i]) {
				gtDFS(i, src, visited);
				gtCnt[src]++;
				ltCnt[i]++;
			}
		}
	}
}
