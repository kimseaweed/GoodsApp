package goods;

import java.util.List;
import java.util.Scanner;

public class GoodsController {
	Scanner sc = new Scanner(System.in);
	int key;
	GoodsDAO dao = new GoodsDAO();
	
	public void mainmenu() {
		
		do{
			System.out.println("******************");
			System.out.println("\t 상품관리");
			System.out.println("******************");
			System.out.println("  1. 입력");
			System.out.println("  2. 검색");
			System.out.println("  3. 삭제");
			System.out.println("  4. 종료");
			System.out.println("******************");
			System.out.print("번호선택 : ");
			key = sc.nextInt();
			if(!(key>0&&key<5)) {
				System.out.println("잘못 입력하셨습니다. 다시입력해주세요.");
				continue;
			}
			if(key==4)break;
			switch(key) {
			case 1 : insert(); continue;
			case 2 : search(); continue;
			case 3 : delete(); continue;
			}
			
		}while(true);
	}

	public void delete() {
		
		System.out.print("삭제를 원하는 제품코드 입력 : ");
		dao.deleteArticle(sc.nextInt());
		
	}

	public void search() {
		do {
		System.out.println("******************");
		System.out.println("    검색");
		System.out.println("******************");
		System.out.println("  1. 전체검색");
		System.out.println("  2. 상품번호 검색");
		System.out.println("  3. 브랜드명 검색");
		System.out.println("  4. 제품명 검색");
		System.out.println("  5. 이전메뉴");
		System.out.println("******************");
		System.out.print("번호선택 : ");
		key = sc.nextInt();
		if(!(key>0&&key<6)) {
			System.out.println("잘못 입력하셨습니다. 다시입력해주세요.");
			continue;
		}
		if(key==5)return;
		
		String value="";
		if(key!=1) {
			if(key==2) {
			System.out.print("번호 입력 : ");
			}else if(key==3){
			System.out.print("브랜드명 입력 :");
			}else {
			System.out.print("제품명 입력 :");	
			}
			value=sc.next();
		}
		List<GoodsDTO> list = dao.searcheArticle(key,value);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		
		
		}while(true);
		
	}

	public void insert() {
		do {
		System.out.println("******************");
		System.out.println("    상품정보입력");
		System.out.println("******************");
		System.out.println("  1. 신상품");
		System.out.println("  2. 이번주 특가");
		System.out.println("  3. 알뜰쇼핑");
		System.out.println("  4. 이전메뉴");
		System.out.println("******************");
		System.out.print("번호선택 : ");
		key = sc.nextInt();
		if(!(key>0&&key<5)) {
			System.out.println("잘못 입력하셨습니다. 다시입력해주세요.");
		}
		if(key==4)return;
		
		GoodsDTO dto = new GoodsDTO();
		dto.setSection(key);
		System.out.print("상품코드 입력 : ");
		dto.setCode(sc.nextInt());
		System.out.print("상품브랜드 입력 : ");
		dto.setBrand(sc.next());
		System.out.print("상품명 입력 : ");
		dto.setName(sc.next());
		System.out.print("상품가격 입력 : ");
		dto.setCost(sc.nextInt());
		if(key==1) {
			System.out.println("할인적용상품 아님");
			dto.setDc(0);
			dto.setFinalCost(dto.getCost());
		}else {
			System.out.print("할인율 소숫점으로 입력 (예시:0.5) : ");
			dto.setDc(sc.nextDouble());
			double finalCost = dto.getCost()-(dto.getCost()*dto.getDc());
			System.out.println("최종 : "+finalCost);
			dto.setFinalCost(finalCost);
		}
		dao.insertArticle(dto);
		}while(true);
	}

	
	
	
}
