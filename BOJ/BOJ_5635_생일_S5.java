package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5635_생일_S5 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n; // 사람 수
		n = Integer.parseInt(br.readLine());

		String oldestName = null;
		String youngestName = null;
		int maxBirthDay = 19900101; // 나이가 가장 적은 사람의 생일
		int minBirthDay = 20101231; // 나이가 가장 많은 사람의 생일

		// 이름 dd mm yyyy
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();

			if(day.length()==1) day="0"+day;
			if(month.length()==1) month="0"+month;
			
			String birthDay = year + month + day;
			int birthDayForCompare = Integer.parseInt(birthDay);

			if (birthDayForCompare >= maxBirthDay) {
				maxBirthDay = birthDayForCompare;
				youngestName = name;
			}
			if (birthDayForCompare <= minBirthDay) {
				minBirthDay = birthDayForCompare;
				oldestName = name;
			}
		}

		System.out.println(youngestName);
		System.out.println(oldestName);
	}
}
