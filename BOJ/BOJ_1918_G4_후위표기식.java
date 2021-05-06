package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1918_G4_후위표기식 {
	static HashMap<Character, Integer>map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		String input = br.readLine();
		String postfix="";
		setMap();
		Stack<Character>stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(c >= 'A' && c<='Z') {	//피연산자이면 그대로 출력
				postfix+=c;
			}else {		
				if(c=='(') stack.push(c);
				else if(c==')') {
					while(!stack.isEmpty() && stack.peek() != '(') {
						postfix+=stack.pop();
					}
					stack.pop();	//'('없애기
				}
				else {	//+ - * / 이다?
					while(!stack.isEmpty() && stack.peek()!='(' && map.get(stack.peek()) >= map.get(c)) {
						postfix+=stack.pop();
					}
					stack.push(c);
				}
			}
		}
		while(!stack.isEmpty()) postfix+=stack.pop();
		
		System.out.println(postfix);
	}
	
	private static void setMap() {
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
	}
}
