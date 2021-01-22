package com.ssafy.ws04.step3;

public class BookTest {
	public static void main(String[] args) {
		BookManager bm = new BookManager();

		// 도서 추가
		bm.add(new Book("21424", "Java Pro", "김하나", "jaen.kr", 15000, "Java 기본문법"));
		bm.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr", 25000, "Java 기본문법"));
		bm.add(new Book("35355", "분석 설계	", "소나무", "jaen.kr", 30000, "SW 모델링"));

		// 도서 목록
		
		for (Book b : bm.getList()) {
			if (b != null) {
				System.out.println(b);
			}
		}
		System.out.println(bm.searchByIsbn("21424"));	//객체 출력
		
		bm.remove("21424");
		
		// 도서 목록	
		for (Book b : bm.getList()) {
			if (b != null) {
				System.out.println(b);	//toString() 오버라이딩 했기 때문.
			}
		}
	}
}
