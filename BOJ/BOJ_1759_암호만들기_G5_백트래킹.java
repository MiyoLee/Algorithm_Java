package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기_G5_백트래킹 {
	static int L;
	static int C;
	static char[] chars;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());	//암호의 길이
		C = Integer.parseInt(st.nextToken());	//암호에 사용된 알파벳의 개수
		
		chars = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			chars[i]=st.nextToken().charAt(0);
		}
		// 입력 완료
		Arrays.sort(chars);	//오름차순 정렬
		
		Comb(0,0,new char[L],0,0);
		
	}
	
	//최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음
	private static void Comb(int cnt, int startIdx, char[] selected, int mo, int ja) {	//모음 갯수, 자음 갯수
		if(L-2<mo)return;
		if(L-1<ja)return;
		if(cnt==L) {
			if(mo>=1 && ja>=2) {
				print(selected);
			}
			return;
		}
		for (int i = startIdx; i < chars.length; i++) {
			selected[cnt]=chars[i];
			if(isMoum(chars[i])) Comb(cnt+1, i+1, selected, mo+1, ja);
			else Comb(cnt+1, i+1, selected, mo, ja+1);
		}
	}

	private static boolean isMoum(char c) {
		if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u') return true;
		return false;
	}

	private static void print(char[] selected) {
		for (int i = 0; i < selected.length; i++) {
			System.out.print(selected[i]);
		}
		System.out.println();
	}
}
