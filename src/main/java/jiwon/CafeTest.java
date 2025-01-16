package jiwon;

import java.util.Scanner;

public class CafeTest {
	public static void printOption() {
        System.out.println("\n******지원 카페 메뉴 관리******");
        System.out.println("1. 전체 메뉴 출력"); //모든 메뉴
        System.out.println("2. 전체 음료 출력"); //음료만
        System.out.println("3. 전체 음식 출력"); //음식만
        System.out.println("4. 메뉴 등록");
        System.out.println("5. 메뉴 검색");
        System.out.println("6. 메뉴 삭제");
        System.out.println("7. 종료");
        System.out.print("메뉴(숫자)를 선택하세요: ");
    }
	
	// 전체 메뉴, 음료, 음식 출력 때만 출력
	public static void printSubOption() {
        System.out.println("\n<<<<<정렬 방법>>>>>");
        System.out.println("1. 높은 가격순");
        System.out.println("2. 낮은 가격순");
        System.out.println("3. 고칼로리순");
        System.out.println("4. 저칼로리순");
        //단, 카테고리별 조회는 다른 정렬 방식을 지원하지 않는다.
        System.out.println("5. 카테고리별"); 
        System.out.println("6. 이전");
        System.out.println("<<<<<<<<>>>>>>>>");
        System.out.print("메뉴(숫자)를 선택하세요: ");
    }
	
	//startCafeProgram()
	public static void main(String[] args){
		MenuManagement cafe = new MenuManagement();
		Scanner scanner = new Scanner(System.in);
		
		// 초기 더미 데이터 추가
        cafe.addDummyData();
		
		while(true) {
			//전체 메뉴 출력
			printOption();
			
			int choice = scanner.nextInt(); //메뉴 선택지 담는 변수 choice
			scanner.nextLine(); // 숫자 입력
			
			switch(choice) {
            case 1: 
            case 2:
            case 3:
            	printSubOption();
            	int sortOption = scanner.nextInt(); //서브 메뉴 선택지 담는 변수 sortOption
                scanner.nextLine();

                String filterType = null;
                if (choice == 2) filterType = "drink";
                if (choice == 3) filterType = "food";
                if (sortOption == 6) break; // 이전으로 돌아가기
                cafe.listMenus(sortOption, filterType);
                break;
            case 4:
            	cafe.addMenu(scanner);
                break;
            case 5: 
            	cafe.searchMenus(scanner);
                break;
            case 6:
            	cafe.deleteMenu(scanner);
                break;
            case 7:
                System.out.println("프로그램을 종료합니다.");
                scanner.close();
                return;
            default:
                System.out.println("잘못된 입력입니다.");
			}
		}
	}
}
