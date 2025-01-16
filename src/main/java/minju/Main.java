package minju;

import java.util.Scanner;

public class Main {
    public static void printApplicationMenu() {
        System.out.println("\n: 사용할 응용프로그램을 선택하세요.");
        System.out.println("1. 이슬아: 여행 경비 정산 시스템");
        System.out.println("2. 이정빈: PhoneBook");
        System.out.println("3. 정민석: 위시리스트 관리 시스템");
        System.out.println("4. 정선우: 밴드(음악) 활동 관리 시스템");
        System.out.println("5. 정성엽: 게시판");
        System.out.println("6. 정주영: 재고 관리 시스템");
        System.out.println("7. 조수빈: 택시팟 관리 시스템");
        System.out.println("8. 조윤수: ");
        System.out.println("9. 채민주: 영화 관리 시스템");
        System.out.println("10. 채지원: 카페 메뉴 관리 시스템");
        System.out.println("11. 종료");
        System.out.print("메뉴를 선택하세요: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieManagement movieManagement = new MovieManagement();

        while (true) {
            printApplicationMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                	System.out.println("영화 관리 시스템으로 이동합니다.");
                    movieManagement.runMenu(scanner); 
                    break;
                case 10:
                    break;
                case 11:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}