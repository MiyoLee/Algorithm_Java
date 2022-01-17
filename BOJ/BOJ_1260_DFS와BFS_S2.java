package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와BFS_S2 {
	static int n,m,v;
	static LinkedList<Integer>[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());	//정점의 개수
		m = Integer.parseInt(st.nextToken());	//간선의 개수
		v = Integer.parseInt(st.nextToken());	//탐색 시작 정점번호
		
		adjList = new LinkedList[n+1];
		for (int i = 0; i < n+1; i++) {
			adjList[i]=new LinkedList();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adjList[v1].add(v2);
			adjList[v2].add(v1);
		}
		
		for (int i = 1; i <= n; i++) {
			if(adjList[i].size()>1) {
				adjList[i].sort(null);
			}
		}
		
		DFS(v, new boolean[n+1]);
		System.out.println();
		BFS(new boolean[n+1]);
	}
	
	private static void DFS(int cur, boolean[]visit) {
		visit[cur]=true;
		System.out.print(cur+" ");
		for (int i = 0; i < adjList[cur].size(); i++) {
			if(!visit[adjList[cur].get(i)]) {
				DFS(adjList[cur].get(i), visit);
			}
		}
	}

	private static void BFS(boolean[] visit) {
		Queue<Integer>queue = new LinkedList<Integer>();
		visit[v]=true;
		queue.add(v);
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			System.out.print(cur+" ");
			
			for (int i = 0; i <adjList[cur].size(); i++) {
				if(!visit[adjList[cur].get(i)]) {
					queue.add(adjList[cur].get(i));
					visit[adjList[cur].get(i)]=true;
				}
			}
		}
		
	}
	
}
