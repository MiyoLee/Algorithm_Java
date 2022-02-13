package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1541_잃어버린괄호_S2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		String stringOperand = ""; //피연산자
		int current = 0;
		int tmp = 0;
		char operator = '+';
		
		int i=0;
		while(i <= input.length()) {		
			if(i < input.length() && input.charAt(i)!='-' && input.charAt(i)!='+') {	// 숫자라면
				stringOperand+=input.charAt(i);
			}else{
				if(operator=='-') {
					current-=Integer.parseInt(stringOperand);
				}else {
					current+=Integer.parseInt(stringOperand);
				}
				if(i < input.length() && input.charAt(i)=='-') {
					operator='-';
				}
				stringOperand="";
			}
			++i;	
		}
		
		System.out.println(current);
	}
}
