package minseok;

import java.util.*;

public class WishlistController {
	public void printMenu() {
		System.out.println("\n위시리스트: ");
		System.out.println("1. 위시리스트 추가");
		System.out.println("2. 전체 위시리스트 출력");
		System.out.println("3. 위시리스트 삭제");
		System.out.println("4. 작성자와 중요도별 위시리스트 조회");
		System.out.println("5. 종료");
		System.out.print("메뉴를 선택하세요 >> ");
	}
	public void run() {
		WishlistManagement list = new WishlistManagement();
		Scanner sc = new Scanner(System.in);
			while(true) {
				printMenu();
				int choice = sc.nextInt();
				switch(choice) {
					case 1:
						list.addWishlist();
						break;
					case 2:
						// 위시리스트 이름 기준으로 출력
						list.printAllWishlists();
						break;
					case 3:
						// 위시리스트 삭제
						list.deleteWishlist();
						break;
					case 4:
						// 작성자와 우선순위로 조회
						list.searchWishlistByImportance();
						break;
					case 5:
						// 종료
						System.out.println("위시리스트 프로그램을 종료합니다.");
						return;
					default:
						System.out.println("잘못된 입력입니다.");
				}
			}
	}
}
