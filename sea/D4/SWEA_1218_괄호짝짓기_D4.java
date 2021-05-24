package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_1218_괄호짝짓기_D4 {
	static int len;
	static String input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int tc = 1; tc <= 10; tc++) {
			len = Integer.parseInt(br.readLine());
			input = br.readLine();
			System.out.println("#"+tc+" "+solve());
		}
	}
	
	//결과 리턴하는 함수
	private static int solve() {
		Stack<Character>stack = new Stack<>();
		
		for (int i = 0; i < len; i++) {
			char c = input.charAt(i);
			if(c=='(' || c=='[' || c=='{' || c=='<') {	//열리는 괄호일때
				stack.push(c);
			}else if(c==')' || c==']' || c=='}' || c=='>') {	//닫히는 괄호일때
				if(c==')') {
					if(!stack.isEmpty() && stack.peek()=='(') stack.pop();
					else return 0;
				}else if(c==']') {
					if(!stack.isEmpty() && stack.peek()=='[') stack.pop();
					else return 0;
				}else if(c=='}') {
					if(!stack.isEmpty() && stack.peek()=='{') stack.pop();
					else return 0;
				}else if(c=='>') {
					if(!stack.isEmpty() && stack.peek()=='<') stack.pop();
					else return 0;
				}
			}
		}
		if(!stack.isEmpty()) return 0;
		return 1;
	}
}
