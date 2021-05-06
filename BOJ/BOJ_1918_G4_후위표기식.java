package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1918_G4_후위표기식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		String input = br.readLine();
		String postfix="";
		
		Stack<Character>stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(c >= 'A' && c<='Z') {	//피연산자이면 그대로 출력
				postfix+=c;
			}else {		
				if(c=='(')continue;
				else if(c==')')postfix+=stack.pop();
				else stack.push(c);
			}
		}
		while(!stack.isEmpty()) postfix+=stack.pop();
		
		System.out.println(postfix);
	}
}
