package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProductTest {
	public static void main(String[] args) throws IOException {
		ProductMgr pm = ProductMgr.getInstance();
		
		/*
		 * pm.add(new Product(1,"삼성tv",1500000,50)); pm.add(new
		 * Product(2,"삼성냉장고",3000000,15)); pm.add(new Product(3,"S21",600000,240));
		 * 
		 * pm.list();
		 * 
		 * pm.priceList(2000000);
		 * 
		 * pm.list(2);
		 * 
		 * pm.delete(1);
		 * 
		 * pm.list();
		 */

		
		// 사용자 입력 받아서 기능 수행.
		pm.add(new Product(1, "삼성tv", 1500000, 50));
		pm.add(new Product(2, "삼성냉장고", 3000000, 15));
		pm.add(new Product(3, "S21", 600000, 240));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			if (!st.hasMoreElements()) {	//엔터누르면 종료.
				System.out.println("종료");
				return;
			}
			String tmp = st.nextToken();

			if (tmp.equals("list")) {
				if (st.hasMoreTokens()) { // 전체 리스트 출력
					pm.list(Integer.parseInt(st.nextToken()));
				} else {
					pm.list();
				}
			} else if (tmp.equals("add")) {
				if (st.hasMoreTokens()) {
					pm.add(new Product(Integer.parseInt(st.nextToken()), st.nextToken(),
							Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				} else {
					System.out.println("추가할 제품 정보를 입력하세요.");
				}
			} else if (tmp.equals("delete")) {
				if (st.hasMoreTokens()) {
					pm.delete(Integer.parseInt(st.nextToken()));
				} else {
					System.out.println("삭제할 제품 정보를 입력하세요.");
				}
			} else if (tmp.equals("priceList")) {
				if (st.hasMoreTokens()) {
					pm.priceList(Integer.parseInt(st.nextToken()));
				} else {
					System.out.println("가격을 입력하세요.");
				}
			}
		}
	}
}
