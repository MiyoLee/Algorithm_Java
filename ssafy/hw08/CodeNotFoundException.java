package com.ssafy.hw08;

public class CodeNotFoundException extends Exception{
	private int num;	//존재하지 않는 isbn
	
	//num 받아 생성하는 생성자
	public CodeNotFoundException(int num) {
		super(num+"번호는 존재하지 않습니다.");
		this.num=num;
	}
//	public int getNum() {
//		return num;
//	}
}
