package com.ssafy.ws04.step3;

import java.util.Arrays;

public class BookManager extends Book {
	final static int MAX_SIZE = 100;
	private Book[] books = new Book[MAX_SIZE];
	private int size = 0; // 책 갯수

	public void add(Book book) {
		if (size<MAX_SIZE) {books[size++] = book;}	// size 초과 확인 해줘야함.
	}

	public void remove(String isbn) { // 고유번호로 도서정보 삭제
		if (size <= 0) {
			System.out.println("삭제할 도서가 없습니다.");
		} else {
			System.out.println("***********************도서삭제:" + isbn + "***********************");
			for(int i=0; i<size; i++) {
				if(books[i].getIsbn().equals(isbn)) {
					books[i] = books[size-1];
					books[size-1]=null;
					//books[i]=null;	//난 이렇게하고 null값 체크해서 main에서 출력 안하게 함.
					break;	//이거 빼먹음.
				}
			}			
			/*for(Book b : books) {		// b는 값만 복사하는거라 안됌!
				if(b.getIsbn().equals(isbn)) {
					b=null;
				}
			}*/	
			size--; // 책 개수 하나인다.
		}
	}

	public Book[] getList() { // 도서리스트 반환
		System.out.println("***********************도서목록***********************");
		return Arrays.copyOfRange(books, 0, size);	//이게 모범답안. size크기의 배열만 넘김.
		// return books; 난 이렇게했음. 되긴 됨
	}

	public Book searchByIsbn(String isbn) {
		System.out.println("***********************도서조회:" + isbn + "***********************");
		for (int i = 0; i < size; i++) {
			if (books[i].getIsbn().equals(isbn)) {	//String 비교할때  .equals() 쓰자!
				return books[i];
			}
		}
		System.out.println("해당 도서는 없습니다.");
		return null;
	}
}
