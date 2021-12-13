package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1254_팰린드롬만들기_S1 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();	// 주어지는 문자열 S. 알파벳 소문자로만 이루어져있음.
		String tmpStr = "";
		for (int i = 0; i < S.length(); i++) {
			tmpStr+=S.charAt(i);
			if(i<S.length()-1) tmpStr+="0";
		}
		
		int len = tmpStr.length();		// tmpStr의 길이. 즉, 마지막 index+1
		int midIdx = len/2;
		
		while(midIdx < len) {
			int i = 1;
			while(midIdx+i < len) {
				if(tmpStr.charAt(midIdx-i) != tmpStr.charAt(midIdx+i)) break;
				else ++i;
			}
			if(midIdx+i == len) {
				System.out.println((midIdx*2+1)/2+1);
				return;
			}else {
				++midIdx;
			}
		}
		
	}
	 
//	static public boolean isPalindrome(String str) {
//		
//		if(str.length()%2==0) return false;
//		
//		return true;
//	}
}
