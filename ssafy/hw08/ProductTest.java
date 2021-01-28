package com.ssafy.hw08;

public class ProductTest {
	public static void main(String[] args) throws DuplicateException, CodeNotFoundException, ProductNotFoundException {
		ProductMgrImpl pm = new ProductMgrImpl();
		
		try {
		 pm.add(new TV(1,"삼성tv",1500000, 10, 50, "LED")); 
		 pm.add(new Refrigerator(2,"삼성냉장고",3000000, 15, 400)); 
		 pm.add(new TV(3,"Utv",600000, 240, 40, "ULED"));
		 pm.add(new Refrigerator(4,"lg냉장고",2000000, 30, 300));
		 pm.add(new Refrigerator(4,"lg냉장고",2000000, 30, 300));
		}catch(DuplicateException e) {
			System.out.println(e.getMessage());
		}
		 
		 System.out.println("*********************전체 상품 리스트*********************");
		 for(Product p : pm.list()) {	//전체 상품 목록 출력
			 System.out.println(p); 
		 }
		 try {
			 System.out.println("*********************상품 번호:2 검색*********************");
			 System.out.println(pm.searchByNum(2));	//상품번호:2 검색
		 }catch(CodeNotFoundException e) {
			 System.out.println(e.getMessage());	 
		 }
		 try {
			 System.out.println("*********************상품 번호:5 검색*********************");
			 System.out.println(pm.searchByNum(5));	//상품번호:5 검색
		 }catch(CodeNotFoundException e) {
			 System.out.println(e.getMessage());
		 } 
		 System.out.println("*****************상품명:삼성 포함 검색*****************");
		 for(Product p : pm.searchByName("삼성")) {	// 이름에'삼성' 포함된 상품 정보 출력
			 System.out.println(p);
		 }
		 
		 System.out.println("****************************TV 정보****************************");
		 for(Product p : pm.getTVs()) {	// TV 정보만 검색
			 System.out.println(p);
		 }
		 
		 System.out.println("************************Refrigerator 정보**********************");
		 for(Product p : pm.getRefrigerators()) {	// Refrigerator 정보만 검색
			 System.out.println(p);
		 }
		 
		 try {
			 System.out.println("*******************400L 이상 Refrigerator 정보********************");
			 for(Product p : pm.getOver400L()) {	// Refrigerator 정보만 검색
				 System.out.println(p);
			 }
		 }catch(ProductNotFoundException e) {
			 System.out.println(e.getMessage());
		 }
		 
		 try {
			 System.out.println("********************50inch 이상 TV 정보********************");
			 for(Product p : pm.getOver50inch()) {	// Refrigerator 정보만 검색
				 System.out.println(p);
			 }
		 }catch(ProductNotFoundException e) {
			 System.out.println(e.getMessage());
		 }
		 System.out.println("*********************상품번호:1 삭제*********************");
		 pm.delete(1);
		 for(Product p : pm.list()) {	//전체 상품 목록 출력
			 System.out.println(p);
		 }
		 
		 try {
			 System.out.println("********************50inch 이상 TV 정보********************");
			 for(Product p : pm.getOver50inch()) {	// Refrigerator 정보만 검색
				 System.out.println(p);
			 }
		 }catch(ProductNotFoundException e) {
			 System.out.println(e.getMessage());
		 }
		 
		 System.out.println("*********************상품번호:2 가격 수정*********************");
		 pm.update(2,3500000);
		 
		 for(Product p : pm.list()) {	//전체 상품 목록 출력
			 System.out.println(p);
		 }
		 
		 System.out.println("전체 재고 상품 금액 : "+pm.getTotalPrice()+"원");

	}
}
