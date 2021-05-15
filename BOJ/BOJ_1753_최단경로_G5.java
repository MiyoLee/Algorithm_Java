package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//다익스트라
public class BOJ_1753_최단경로_G5 {
	static class Node implements Comparable<Node>{
		int to; // 노드 번호
		int weight; // 시작점에서 자기 자신까지 거리

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}

	static int V, E;
	static int start;
	static List<Node>[] adjList;
	static int[] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken()); // 정점 갯수
		E = Integer.parseInt(st.nextToken()); // 간선 갯수
		start = Integer.parseInt(br.readLine()); // 시작 정점

		adjList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight));
		}
		// 인접 리스트 입력 완료.
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		dijkstra();
		print();
	}

	private static void dijkstra() {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V+1];	//경유지로 선택 했는지 안했는지
		pq.offer(new Node(start,0));
		distance[start]=0;
		
		while(!pq.isEmpty()) {
			//1. distance중 최솟값을 선택한다.
			Node curNode = pq.poll();
			int cur = curNode.to;
			
			if(visited[cur]) continue;
			visited[cur] = true;
			
			//2. current를 경유하는 노드
			for (Node node : adjList[cur]) {
				if(distance[node.to] > distance[cur] + node.weight) {
					distance[node.to] = distance[cur] + node.weight;
					pq.offer(new Node(node.to, distance[node.to]));
				}
			}
		}

	}
	private static void print() {
		for (int i = 1; i <= V; i++) {
			if (distance[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}
	}
}
